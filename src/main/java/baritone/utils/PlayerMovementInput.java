/*
 * This file is part of Baritone.
 *
 * Baritone is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Baritone is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Baritone.  If not, see <https://www.gnu.org/licenses/>.
 */

package baritone.utils;

import baritone.api.utils.input.Input;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.NodeEvaluator;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlayerMovementInput extends baritone.utils.Input {

    private final InputOverrideHandler handler;
    private PathfinderMob mob;

    public static final float MIN_SPEED = 5.0E-4F;
    public static final float MIN_SPEED_SQR = 2.5000003E-7F;
    protected static final int MAX_TURN = 90;
    protected double wantedX;
    protected double wantedY;
    protected double wantedZ;
    protected double speedModifier = 1.0f;
    protected float strafeForwards;
    protected float strafeRight;
    protected Operation operation;

    PlayerMovementInput(InputOverrideHandler handler, PathfinderMob mob) {
        this.handler = handler;
        this.mob = mob;
    }

    @Override
    public void tick(boolean p_225607_1_) {
        float leftSpeed = 0.0F;
        float forwardSpeed = 0.0F;

        this.jumping = handler.isInputForcedDown(Input.JUMP); // oppa gangnam style

//        if (this.up == handler.isInputForcedDown(Input.MOVE_FORWARD)) {
//            this.forwardImpulse++;
//        }
//
//        if (this.down == handler.isInputForcedDown(Input.MOVE_BACK)) {
//            this.forwardImpulse--;
//        }
//
//        if (this.left == handler.isInputForcedDown(Input.MOVE_LEFT)) {
//            this.leftImpulse++;
//        }
//
//        if (this.right == handler.isInputForcedDown(Input.MOVE_RIGHT)) {
//            this.leftImpulse--;
//        }
//
//        if (this.shiftKeyDown == handler.isInputForcedDown(Input.SNEAK)) {
//            this.leftImpulse *= 0.3D;
//            this.forwardImpulse *= 0.3D;
//        }
//            this.wantedX = this.mob.getLookControl().getWantedX();
//            this.wantedY = this.mob.getLookControl().getWantedY();
//            this.wantedZ = this.mob.getLookControl().getWantedZ();
//            operation = Operation.MOVE_TO;

        if(handler.isInputForcedDown(Input.MOVE_FORWARD)){
            forwardSpeed = 1.0f;
        } else if (handler.isInputForcedDown(Input.MOVE_BACK)){
            forwardSpeed = -1.0f;
        }

        if(handler.isInputForcedDown(Input.MOVE_LEFT)){
            leftSpeed = 1.0f;
        } else if (handler.isInputForcedDown(Input.MOVE_RIGHT)){
            leftSpeed = -1.0f;
        }

        mob.getMoveControl().strafe(forwardSpeed, leftSpeed);

        if(this.jumping) {
            this.mob.getJumpControl().jump();
        }
    }

    public boolean hasWanted() {
        return this.operation == Operation.MOVE_TO;
    }

    public double getSpeedModifier() {
        return this.speedModifier;
    }

    public void setWantedPosition(double var0, double var2, double var4, double var6) {
        this.wantedX = var0;
        this.wantedY = var2;
        this.wantedZ = var4;
        this.speedModifier = var6;
        if (this.operation != Operation.JUMPING) {
            this.operation = Operation.MOVE_TO;
        }

    }

    public void strafe(float forwards, float right) {
        this.operation = Operation.STRAFE;
        this.strafeForwards = forwards;
        this.strafeRight = right;
        this.speedModifier = 0.25;
    }

    public void handleMoveTick() {
        float xzWantedMoveAngle;
        if (this.operation == Operation.STRAFE) {
            float baseMovementSpeed = (float)this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED);
            float modifiedMovementSpeed = (float)this.speedModifier * baseMovementSpeed;
            float strafeForwards = this.strafeForwards;
            float strafeRight = this.strafeRight;
            float strafeNormalized = Mth.sqrt(strafeForwards * strafeForwards + strafeRight * strafeRight);
            if (strafeNormalized < 1.0F) {
                strafeNormalized = 1.0F;
            }
            strafeNormalized = modifiedMovementSpeed / strafeNormalized;
            strafeForwards *= strafeNormalized;
            strafeRight *= strafeNormalized;
            float sinYRot = Mth.sin(this.mob.getYRot() * 0.017453292F); // facing block??
            float cosYRot = Mth.cos(this.mob.getYRot() * 0.017453292F); // adjacent block??
            float var7 = strafeForwards * cosYRot - strafeRight * sinYRot;
            xzWantedMoveAngle = strafeRight * cosYRot + strafeForwards * sinYRot;
            if (!this.isWalkable(var7, xzWantedMoveAngle)) {
                this.strafeForwards = 1.0F; // walk straight ahead??
                this.strafeRight = 0.0F;
            }
            this.mob.setSpeed(modifiedMovementSpeed);
            this.mob.setZza(this.strafeForwards);
            this.mob.setXxa(this.strafeRight);
            this.operation = Operation.WAIT;


        } else if (this.operation == Operation.MOVE_TO) {

            this.operation = Operation.WAIT;
            double dX = this.wantedX - this.mob.getX();
            double dZ = this.wantedZ - this.mob.getZ();
            double dY = this.wantedY - this.mob.getY();
            double distanceSquared = dX * dX + dY * dY + dZ * dZ;
            if (distanceSquared < 2.5E-7) {
                this.mob.setZza(0.0F);
                return;
            }
            xzWantedMoveAngle = (float)(Mth.atan2(dZ, dX) * 57.2957763671875) - 90.0F;
            this.mob.setYRot(this.rotlerp(this.mob.getYRot(), xzWantedMoveAngle, 90.0F));
            this.mob.setSpeed((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
            BlockPos currentBlockPos = this.mob.blockPosition();
            BlockState currentBlockState = this.mob.level.getBlockState(currentBlockPos);
            VoxelShape currentBlockHitbox = currentBlockState.getCollisionShape(this.mob.level, currentBlockPos);
            if (
                    dY > (double)this.mob.maxUpStep && // can step up the needed height
                            dX * dX + dZ * dZ < (double)Math.max(1.0F, this.mob.getBbWidth()) || // AND squared distance is less than mob size (or 1 if less than 1)
                            /* OR */
                            !currentBlockHitbox.isEmpty() && // current block isn't empty
                                    this.mob.getY() < currentBlockHitbox.max(Direction.Axis.Y) + (double)currentBlockPos.getY() && // current block pos is less than maximum of wanted block?
                                    !currentBlockState.is(BlockTags.DOORS) && // and it isn't a door
                                    !currentBlockState.is(BlockTags.FENCES)) { // or a fence

                this.mob.getJumpControl().jump();
                this.operation = Operation.JUMPING;
            }


        } else if (this.operation == Operation.JUMPING) {
            this.mob.setSpeed((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
            if (this.mob.isOnGround()) {
                this.operation = Operation.WAIT;
            }
        } else {
            this.mob.setZza(0.0F);
        }

    }

    private boolean isWalkable(float var0, float var1) {
        PathNavigation var2 = this.mob.getNavigation();
        if (var2 != null) {
            NodeEvaluator var3 = var2.getNodeEvaluator();
            if (var3 != null && var3.getBlockPathType(this.mob.level, Mth.floor(this.mob.getX() + (double)var0), this.mob.getBlockY(), Mth.floor(this.mob.getZ() + (double)var1)) != BlockPathTypes.WALKABLE) {
                return false;
            }
        }

        return true;
    }

    /** A lot of sanity checks to basically return xzWantedMoveAngle */
    protected float rotlerp(float yRot, float xzWantedMoveAngle, float ninety) {
        float var3 = Mth.wrapDegrees(xzWantedMoveAngle - yRot);
        if (var3 > ninety) {
            var3 = ninety;
        }

        if (var3 < -ninety) {
            var3 = -ninety;
        }

        float var4 = yRot + var3;
        if (var4 < 0.0F) {
            var4 += 360.0F;
        } else if (var4 > 360.0F) {
            var4 -= 360.0F;
        }

        return var4;
    }

    protected static enum Operation {
        WAIT,
        MOVE_TO,
        STRAFE,
        JUMPING;

        private Operation() {
        }
    }
}
