package org.firstinspires.ftc.teamcode.AutoPrograms;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.teamcode.MecanumTest_Hardware;

/**
 * Created by honul on 1/25/2018.
 */
@Autonomous(name = "RedJewel", group = "Auto")
public class RedJewel extends LinearOpMode {

    MecanumTest_Hardware robot = new MecanumTest_Hardware();
    public ElapsedTime runtime = new ElapsedTime();

    public enum DIRECTION {F, L, R, B};

    VuforiaLocalizer vuforia;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Starting Op");
        telemetry.update();

        waitForStart();

        runtime.reset();

        blueJewelHitter();

        delay(3);
    }

    public void driveTime(double power, double time, int path, Blue1.DIRECTION d){

        if(d == Blue1.DIRECTION.F){
            robot.leftFrontDrive.setPower(power);
            robot.leftBackDrive.setPower(power);
            robot.rightFrontDrive.setPower(power);
            robot.rightBackDrive.setPower(power);
        }else if (d == Blue1.DIRECTION.L){
            robot.leftFrontDrive.setPower(power * 1);
            robot.leftBackDrive.setPower(-power);
            robot.rightFrontDrive.setPower(-power * 1);
            robot.rightBackDrive.setPower(power);
        }else if (d == Blue1.DIRECTION.R){
            robot.leftFrontDrive.setPower(-power * 1);
            robot.leftBackDrive.setPower(power);
            robot.rightFrontDrive.setPower(power * 1);
            robot.rightBackDrive.setPower(-power);
        }else{
            robot.leftFrontDrive.setPower(-power);
            robot.leftBackDrive.setPower(-power);
            robot.rightFrontDrive.setPower(-power);
            robot.rightBackDrive.setPower(-power);
        }


        runtime.reset();
        while(runtime.seconds() < time) {
            telemetry.addData("Path", "Running:%7d", path);
            telemetry.update();
        }
        robot.leftFrontDrive.setPower(0);
        robot.leftBackDrive.setPower(0);
        robot.rightFrontDrive.setPower(0);
        robot.rightBackDrive.setPower(0);
        telemetry.addData("Path", "Completed:%7d", path);
        telemetry.update();
    }

    public void rotTime(double power, double time, int path, Blue1.DIRECTION d){
        if(d == Blue1.DIRECTION.L){
            robot.leftFrontDrive.setPower(-power);
            robot.leftBackDrive.setPower(-power);
            robot.rightFrontDrive.setPower(power);
            robot.rightBackDrive.setPower(power);
        }else if (d == Blue1.DIRECTION.R){
            robot.leftFrontDrive.setPower(power);
            robot.leftBackDrive.setPower(power);
            robot.rightFrontDrive.setPower(-power);
            robot.rightBackDrive.setPower(-power);
        }

        runtime.reset();
        while(runtime.seconds() < time) {
            telemetry.addData("Path", "Running:%7d", path);
            telemetry.update();
        }
        robot.leftFrontDrive.setPower(0);
        robot.leftBackDrive.setPower(0);
        robot.rightFrontDrive.setPower(0);
        robot.rightBackDrive.setPower(0);
        telemetry.addData("Path", "Completed:%7d", path);
        telemetry.update();

    }

    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }

    public void blueJewelHitter() {

        robot.jewelHitter.setPosition(0.5);
        delay (1);
        robot.jewelRot.setPosition(0.5);
        delay(1);
        robot.jewelHitter.setPosition(0.9);
        delay(1);

        robot.jewelSensor.enableLed(true);
        runtime.reset();
        while(runtime.seconds() < 1) {
            telemetry.addData("RGB", "%7d, %7d, %7d", robot.jewelSensor.red(), robot.jewelSensor.green(), robot.jewelSensor.blue());
            telemetry.update();
        }

        if(robot.jewelSensor.blue() > robot.jewelSensor.red()){
            robot.jewelRot.setPosition(0.9);
        }else{
            robot.jewelRot.setPosition(0.1);
        }
        delay(1);

        robot.jewelHitter.setPosition(0.35);
        robot.jewelRot.setPosition(0.5);
        delay(1);
    }

    public void delay (double time)
    {
        runtime.reset();
        while(runtime.seconds() < time){}
    }
}

