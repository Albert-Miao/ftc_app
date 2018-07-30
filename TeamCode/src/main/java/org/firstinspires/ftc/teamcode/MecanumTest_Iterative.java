package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Albert on 10/3/2017.
 */

@TeleOp(name="Mecanum: Test", group="Mecanum")

public class MecanumTest_Iterative extends OpMode{

    MecanumTest_Hardware robot = new MecanumTest_Hardware();
    public ElapsedTime runtime = new ElapsedTime();


    boolean collectorRunning = false;
    boolean guidePressedPrev = false;

    @Override
    public void init(){
        robot.init(hardwareMap);
        gamepad1.setJoystickDeadzone(0);
        gamepad2.setJoystickDeadzone(0);

//        robot.lift.setPower(-0.3);
//
//        runtime.reset();
//        while(runtime.seconds() < 0.5){
//            telemetry.addData("Lift Height", robot.lift.getCurrentPosition());
//            telemetry.update();
//        }
//
//        robot.lift.setPower(0);
//
//        liftMin = robot.lift.getCurrentPosition();

        robot.jewelRot.setPosition(0.8);
    }

    @Override
    public void init_loop(){

    }

    @Override
    public void start(){

    }

    @Override
    public void loop() {

        double x = -gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;
        double rot = -gamepad1.right_stick_x;

        robot.jewelHitter.setPosition(0.5);
        robot.jewelRot.setPosition(0.5);

      /*


        if(lastButton == true && !gamepad1.a) collOn = collOn;
        lastButton = gamepad1.a;

        if(collOn){
        robot.leftCollector.setPower(1);
        robot.rightCollector.setPower(1);
        }else{
        robot.leftCollector.setPower(0);
        robot.rightCollector.setPower(0);
        }
        telemetry.addData("Red  ", robot.jewelSensor.red());
        telemetry.addData("Green", robot.jewelSensor.green());
        telemetry.addData("Red1 ", robot.jewelSensor.blue());

        if(gamepad1.x)
        if (robot.jewelSensor.blue() > robot.jewelSensor.red()) robot.jewelHitter.setPosition(1);
        else robot.jewelHitter.setPosition(0);
        */


        robot.leftFrontDrive.setPower(y - (x * 1) - rot);
        robot.leftBackDrive.setPower(y + x - rot);
        robot.rightFrontDrive.setPower(y + (x * 1) + rot);
        robot.rightBackDrive.setPower(y - x + rot);

        //if (gamepad1.a && robot.lift.getCurrentPosition() < liftMin) robot.lift.setPower(-1);
        if (gamepad2.right_bumper) robot.lift.setPower(-1);
        else if (gamepad2.left_bumper) robot.lift.setPower(1);
        else robot.lift.setPower(0);
//        telemetry.addData("Lift Height", -robot.lift.getCurrentPosition());
//        telemetry.update();

        if (gamepad2.y) robot.ramp.setPosition(.9);
        else if (gamepad2.b)robot.ramp.setPosition(.67);
        else if (gamepad2.a)robot.ramp.setPosition(.1);


        if (gamepad1.dpad_up) {
            robot.leftFrontDrive.setPower(0.4);
            robot.rightFrontDrive.setPower(0.4);
            robot.leftBackDrive.setPower(0.4);
            robot.rightBackDrive.setPower(0.4);
        } else if (gamepad1.dpad_down) {
            robot.leftFrontDrive.setPower(-0.4);
            robot.rightFrontDrive.setPower(-0.4);
            robot.leftBackDrive.setPower(-0.4);
            robot.rightBackDrive.setPower(-0.4);
        } else if (gamepad1.dpad_left) {
            robot.leftFrontDrive.setPower(-0.4);
            robot.rightFrontDrive.setPower(0.4);
            robot.leftBackDrive.setPower(0.4);
            robot.rightBackDrive.setPower(-0.4);
        } else if (gamepad1.dpad_right) {
            robot.leftFrontDrive.setPower(0.4);
            robot.rightFrontDrive.setPower(-0.4);
            robot.leftBackDrive.setPower(-0.4);
            robot.rightBackDrive.setPower(0.4);
        }


//
//        if(gamepad2.guide){
//            guidePressedPrev = true;
//        }else if (guidePressedPrev){
//            guidePressedPrev = false;
//            if(collectorRunning){
//                robot.left_collector.setPower(0);
//                robot.right_collector.setPower(0);
//                collectorRunning = false;
//            }else{
//                robot.left_collector.setPower(1);
//                robot.right_collector.setPower(1);
//                collectorRunning = true;
//            }
//        }





    }

    @Override
    public void stop(){

    }

}
