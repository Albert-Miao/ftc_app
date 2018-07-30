package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Albert on 10/3/2017.
 */

public class MecanumTest_Hardware {
    public DcMotor leftFrontDrive = null;
    public DcMotor leftBackDrive = null;
    public DcMotor rightFrontDrive = null;
    public DcMotor rightBackDrive = null;
    public ColorSensor jewelSensor = null;
    public Servo jewelHitter = null;
    public Servo jewelRot = null;
    public Servo ramp = null;
//    public DcMotor left_collector = null;
//    public DcMotor right_collector = null;
    public DcMotor lift = null;

    HardwareMap hwmap = null;
    private ElapsedTime period = new ElapsedTime();

    public MecanumTest_Hardware(){}

    public void init(HardwareMap ahwmap){
        hwmap = ahwmap;

        leftFrontDrive = hwmap.get(DcMotor.class, "left_front_motor");
        leftBackDrive = hwmap.get(DcMotor.class, "left_back_motor");
        rightFrontDrive = hwmap.get(DcMotor.class, "right_front_motor");
        rightBackDrive = hwmap.get(DcMotor.class, "right_back_motor");
//        left_collector = hwmap.get(DcMotor.class, "left_collector");
//        right_collector = hwmap.get(DcMotor.class, "right_collector");
        lift = hwmap.get(DcMotor.class, "lift");

        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
//        left_collector.setDirection(DcMotor.Direction.FORWARD);
//        right_collector.setDirection(DcMotor.Direction.REVERSE);
        lift.setDirection(DcMotor.Direction.FORWARD);

        leftFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightFrontDrive.setPower(0);
        rightBackDrive.setPower(0);
//        left_collector.setPower(0);
//        right_collector.setPower(0);
        lift.setPower(0);

        leftFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        left_collector.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        right_collector.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        jewelHitter = hwmap.get(Servo.class, "jewel_hitter");
        jewelRot = hwmap.get(Servo.class, "jewel_rot");
        ramp = hwmap.get(Servo.class, "ramp");
        jewelSensor = hwmap.get(ColorSensor.class, "jewel_sensor");

    }
}
