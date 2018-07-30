package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by dylanbienstock on 11/25/17.
 */

@Autonomous(name = "Auto", group = "Auto")

public class AutoV1 extends LinearOpMode {
    MecanumTest_Hardware robot = new MecanumTest_Hardware();
    public ElapsedTime runtime = new ElapsedTime();

    VuforiaLocalizer vuforia;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        waitForStart();
        runtime.reset();
        while(runtime.seconds() < 10) {
            int red = robot.jewelSensor.red();
            int green = robot.jewelSensor.green();
            int blue = robot.jewelSensor.blue();

            telemetry.addData("RGB", "%7d, %7d, %7d", red, green, blue);
            telemetry.update();
        }

//        robot.rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        int newTarget = robot.rightFrontDrive.getCurrentPosition() + 720;
//        robot.rightFrontDrive.setTargetPosition(newTarget);
//        runtime.reset();
//        robot.rightFrontDrive.setPower(0.3);
//        robot.leftFrontDrive.setPower(0.3);
//        robot.leftBackDrive.setPower(0.3);
//        robot.rightBackDrive.setPower(0.3);
//        while(opModeIsActive() && runtime.seconds() < 30 && robot.rightFrontDrive.isBusy()){
//            telemetry.addData("encoder tics", robot.rightFrontDrive.getCurrentPosition());
//            telemetry.update();
//        }
//
//        robot.rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        robot.rightFrontDrive.setPower(0);
//        robot.leftFrontDrive.setPower(0);
//        robot.leftBackDrive.setPower(0);
//        robot.rightBackDrive.setPower(0);
    }
}
