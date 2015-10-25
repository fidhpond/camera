package ic.doc.camera;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class CameraTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    final Sensor sensor = context.mock(Sensor.class);
    final MemoryCard mem = context.mock(MemoryCard.class);
    Camera camera = new Camera(sensor, mem);
    byte[] datas = new byte[10];

    @Test
    public void switchingTheCameraOnPowersUpTheSensor() {

        context.checking(new Expectations() {{
            exactly(1).of(sensor).powerUp();
        }});
        camera.powerOn();
         // write your test here
    }

    @Test
    public void switchingTheCameraOffPowersDownTheSensor() {

        context.checking(new Expectations() {{
            exactly(1).of(sensor).powerUp();
            exactly(1).of(sensor).powerDown();
        }});
        camera.powerOn();
        camera.powerOff();
    }

    @Test
    public void pressingShutterWhenPowerOff() {

        context.checking(new Expectations() {{
            exactly(1).of(sensor).powerUp();
            exactly(1).of(sensor).powerDown();
            never(sensor).readData();
        }});
        camera.powerOn();
        camera.powerOff();
        camera.pressShutter();
    }

    @Test
    public void pressingShutterWhenPowerOn(){
        context.checking(new Expectations() {{
            exactly(1).of(sensor).readData();
            will(returnValue(datas));
            exactly(1).of(sensor).powerUp();
            exactly(1).of(mem).write(datas);
        }});
        camera.powerOn();
        camera.pressShutter();
    }

    @Test
    public void switchOffSensorTest(){
        context.checking(new Expectations() {{
            exactly(1).of(sensor).powerDown();
            exactly(2).of(sensor).powerUp();
        }});
        camera.powerOn();
        camera.writeCompleted = true;
        camera.powerOff();
        camera.powerOn();
        camera.writeCompleted = false;
        camera.powerOff();
    }

    @Test
    public void sensorTurnsOffOnceWritingDone(){
        context.checking(new Expectations(){{
            exactly(1).of(sensor).powerUp();
            exactly(1).of(sensor).readData();
            will(returnValue(datas));
            exactly(1).of(mem).write(datas);
            exactly(1).of(sensor).powerDown();
        }});
        camera.powerOn();
        camera.pressShutter();
        camera.writeCompleted = false;
        camera.powerOff();
        camera.writeComplete();
    }
}
