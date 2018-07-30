package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Albert on 10/3/2017.
 */

@TeleOp(name="Testing", group="Mecanum")

public class Testing extends OpMode{

    MecanumTest_Hardware robot = new MecanumTest_Hardware();
    public ElapsedTime runtime = new ElapsedTime();

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

    }

    @Override
    public void init_loop(){

    }

    @Override
    public void start(){

    }

    @Override
    public void loop() {


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


        if(gamepad1.dpad_up) robot.leftFrontDrive.setPower(0.2);
        else robot.leftFrontDrive.setPower(0);
        if(gamepad1.dpad_down) robot.leftBackDrive.setPower(0.2);
        else robot.leftBackDrive.setPower(0);
        if (gamepad1.dpad_left) robot.rightFrontDrive.setPower(0.2);
        else robot.rightFrontDrive.setPower(0);
        if (gamepad1.dpad_right) robot.rightBackDrive.setPower(0.2);
        else robot.rightBackDrive.setPower(0);


        //if (gamepad1.a && robot.lift.getCurrentPosition() < liftMin) robot.lift.setPower(-1);
        if (gamepad1.a) robot.lift.setPower(1);
        else if (gamepad1.b) robot.lift.setPower(-1);
        else robot.lift.setPower(0);
//        telemetry.addData("Lift Height", -robot.lift.getCurrentPosition());
//        telemetry.update();


//        if (gamepad1.dpad_up) {
//            robot.left_collector.setPower(1);
//        } else if (gamepad1.dpad_down) {
//            robot.left_collector.setPower(-1);
//        } else if (gamepad1.dpad_left) {
//            robot.right_collector.setPower(1);
//        } else if (gamepad1.dpad_right) {
//            robot.right_collector.setPower(-1);
//        }


        if(gamepad1.x) robot.jewelHitter.setPosition(0);
        if(gamepad1.y) robot.jewelHitter.setPosition(1);
        if(gamepad1.left_bumper) robot.jewelRot.setPosition(0);
        if(gamepad1.right_bumper) robot.jewelRot.setPosition(1);

        if(gamepad1.guide) robot.ramp.setPosition(0.67);
        if(gamepad1.start) robot.ramp.setPosition(0.9);
        if(gamepad1.back) robot.ramp.setPosition(0.1);



    }

    @Override
    public void stop(){

    }

}