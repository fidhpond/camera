package ic.doc.camera;

public class Camera implements WriteListener{

    Sensor sensor;
    MemoryCard mem;
    boolean powerOn = false;

    public Camera(Sensor sensor, MemoryCard mem){
        this.sensor = sensor;
        this.mem = mem;
    }

    public void pressShutter() {
        if(powerOn){
            mem.write(sensor.readData());
        }
    }

    public void powerOn() {
        sensor.powerUp();
        powerOn = true;
    }

    public void powerOff() {
       sensor.powerDown();
        powerOn = false;
    }

    @Override
    public void writeComplete() {

    }
}

