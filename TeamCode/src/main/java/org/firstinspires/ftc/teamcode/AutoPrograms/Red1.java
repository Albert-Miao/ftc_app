package org.firstinspires.ftc.teamcode.AutoPrograms;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.MecanumTest_Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by dylanbienstock on 11/26/17.
 */
//hit jewel and scan, move forward, strife to desired location, place block in area


@Autonomous(name = "Red1", group = "Auto")

public class Red1 extends LinearOpMode {
    MecanumTest_Hardware robot = new MecanumTest_Hardware();
    public ElapsedTime runtime = new ElapsedTime();

    public enum DIRECTION {F, L, R, B};

    VuforiaLocalizer vuforia;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "ARB6iBL/////AAAAGXVZ2BYkNk9ej0ujM1TgUKh57J+xCID7Tv2U0YZpZnBl93xUHD3wt6MGtv61eQGgGvghHhvbLWF9ln3i7ezy67++B3zvc8GHRrcEeAxQt0LkoYSbAI6iTarRnR/EXyac/0649Xaqfj5suYnHgKQJFqUAFCwMXuwmUd1lZscRZ8Ift8vYoRJaNsK1kRHlwi+/XKdJS5A92FBItbQJp8rdZDCwUxbc9FovmyXgOS5p0gyfcw3D5O/48g8uP0r3kgEHOMFv+n/88dqhYZ4/Xo0Jcwp5qGueiMWlDjpwphHKg/WoZeM6Bl4BLRV0Ie3jAdghEQCDAXjwz3fTiLaKAeKjCcgF7BkPmPCsNm6aLP8+wfb+";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");

        RelicRecoveryVuMark vuMark=null;

        telemetry.addData("Status", "Starting Op");
        telemetry.update();

        waitForStart();

        runtime.reset();

//        relicTrackables.activate();
//
//        while (runtime.seconds() < 10) {
//
//            /**
//             * See if any of the instances of {@link relicTemplate} are currently visible.
//             * {@link RelicRecoveryVuMark} is an enum which can have the following values:
//             * UNKNOWN, LEFT, CENTER, and RIGHT. When a VuMark is visible, something other than
//             * UNKNOWN will be returned by {@link RelicRecoveryVuMark#from(VuforiaTrackable)}.
//             */
//            vuMark = RelicRecoveryVuMark.from(relicTemplate);
//            //if (vuMark != RelicRecoveryVuMark.UNKNOWN) {
//            if(true){
//
//                /* Found an instance of the template. In the actual game, you will probably
//                 * loop until this condition occurs, then move on to act accordingly depending
//                 * on which VuMark was visible. */
//                telemetry.addData("VuMark", "%s visible", vuMark);
//
//                /* For fun, we also exhibit the navigational pose. In the Relic Recovery game,
//                 * it is perhaps unlikely that you will actually need to act on this pose information, but
//                 * we illustrate it nevertheless, for completeness. */
//                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener)relicTemplate.getListener()).getPose();
//                telemetry.addData("Pose", format(pose));
//
//                /* We further illustrate how to decompose the pose into useful rotational and
//                 * translational components */
//                if (pose != null) {
//                    VectorF trans = pose.getTranslation();
//                    Orientation rot = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);
//
//                    // Extract the X, Y, and Z components of the offset of the target relative to the robot
//                    double tX = trans.get(0);
//                    double tY = trans.get(1);
//                    double tZ = trans.get(2);
//
//                    // Extract the rotational components of the target relative to the robot
//                    double rX = rot.firstAngle;
//                    double rY = rot.secondAngle;
//                    double rZ = rot.thirdAngle;
//                }
//            }
//            else if(gamepad1.guide) telemetry.addData("VuMark", "test");
//            else {
//                telemetry.addData("VuMark", "not visible");
//            }
//
//            telemetry.update();
//        }
//
//        telemetry.addData("Path", "Completed:1");
//        telemetry.update();

        redJewelHitter();

        driveTime(0.2, 2.4, 3, DIRECTION.B);
        driveTime(0.4, 0.5, 5, DIRECTION.R);
        driveTime(0.1, 0.5, 6, DIRECTION.B);

        robot.ramp.setPosition(0.67);
        delay(1);
        robot.lift.setPower(1);
        delay(0.7);
        robot.lift.setPower(0);
        robot.ramp.setPosition(0.1);
        delay(1);
        robot.ramp.setPosition(0.9);
        delay(1);
        robot.ramp.setPosition(0.1);

        driveTime(0.2, 0.5, 7, DIRECTION.F);
        driveTime(0.2, 0.8, 8, DIRECTION.B);
        driveTime(0.2, 0.5, 9, DIRECTION.F);
        driveTime(0.2, 0.8, 10, DIRECTION.B);
        driveTime(0.2, 0.6, 11, DIRECTION.F);
        
//
//        if(vuMark == RelicRecoveryVuMark.LEFT){
//            driveTime(0.4, 0.3, 4, DIRECTION.R);
//        }else if(vuMark == RelicRecoveryVuMark.CENTER){
//            driveTime(0.4, 0.8, 4, DIRECTION.R);
//        }else if(vuMark == RelicRecoveryVuMark.RIGHT){
//            driveTime(0.4, 1.3, 4, DIRECTION.R);
//        }

        delay(3);

    }

    public void driveTime(double power, double time, int path, DIRECTION d){

        if(d == DIRECTION.F){
            robot.leftFrontDrive.setPower(power);
            robot.leftBackDrive.setPower(power);
            robot.rightFrontDrive.setPower(power);
            robot.rightBackDrive.setPower(power);
        }else if (d == DIRECTION.L){
            robot.leftFrontDrive.setPower(-power * 1);
            robot.leftBackDrive.setPower(power);
            robot.rightFrontDrive.setPower(power * 1);
            robot.rightBackDrive.setPower(-power);
        }else if (d == DIRECTION.R){
            robot.leftFrontDrive.setPower(power * 1);
            robot.leftBackDrive.setPower(-power);
            robot.rightFrontDrive.setPower(-power * 1);
            robot.rightBackDrive.setPower(power);
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

    public void rotTime(double power, double time, int path, DIRECTION d){
        if(d == DIRECTION.L){
            robot.leftFrontDrive.setPower(-power);
            robot.leftBackDrive.setPower(-power);
            robot.rightFrontDrive.setPower(power);
            robot.rightBackDrive.setPower(power);
        }else if (d == DIRECTION.R){
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

    public void redJewelHitter() {

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
