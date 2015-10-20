package ic.doc.camera;

public class Camera implements WriteListener{

    Sensor sensor;

    public Camera(Sensor sensor){
        this.sensor = sensor;
    }

    public void pressShutter() {
        // not implemented
    }

    public void powerOn() {
        sensor.powerUp();
    }

    public void powerOff() {
       sensor.powerDown();
    }

    @Override
    public void writeComplete() {

    }
}

