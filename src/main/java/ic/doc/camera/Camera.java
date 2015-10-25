package ic.doc.camera;

public class Camera implements WriteListener{

    Sensor sensor;
    MemoryCard mem;
    boolean powerOn = false;
    boolean writeCompleted = true;

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
        if(writeCompleted == true){
            sensor.powerDown();
        }
        powerOn = false;
    }

    @Override
    public void writeComplete() {
        writeCompleted = true;
        if(powerOn == false){
            sensor.powerDown();
        }
    }
}

