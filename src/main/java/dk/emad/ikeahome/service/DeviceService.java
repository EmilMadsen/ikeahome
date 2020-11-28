package dk.emad.ikeahome.service;

import nl.stijngroenen.tradfri.device.Device;
import nl.stijngroenen.tradfri.device.Gateway;
import nl.stijngroenen.tradfri.device.Light;
import nl.stijngroenen.tradfri.util.Credentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DeviceService {

    @Value("${secretip:none}") private String ip;
    @Value("${secretcode:none}") private String code;
    private Gateway gateway;

    @PostConstruct
    public void setup() {
        System.out.println("Ip is: " + ip);
        System.out.println("Code is: " + code);
        gateway = new Gateway(ip);
        Credentials credentials = gateway.connect(code);
        gateway.connect(credentials);
    }

    public Device getById(int id) {
        return gateway.getDevice(id);
    }

    public Light setById(int id, boolean on, int brightness) {
        Light light = (Light) gateway.getDevice(id);
        light.setOn(on);
        light.setBrightness(brightness);
        return light;
    }

    public List<Device> getByName(String name) {
        return getLightsContainingName(gateway.getDevices(), name);
    }

    public List<Device> setByName(String name, boolean on, int brightness) {
        List<Device> lights = getByName(name);
        setLightsToState(lights, on, brightness);
        return lights;
    }

    public Device[] getAll() {
        return gateway.getDevices();
    }

    public Device[] setAll(boolean on, int brightness) {
        Device[] devices = gateway.getDevices();
        setLightsToState(Arrays.asList(devices), on, brightness);
        return devices;
    }

    private void setLightsToState(List<Device> devices, boolean on, int brightness) {
        for(Device device: devices){
            if (device.isLight()){
                Light light = device.toLight();
                light.setOn(on);
                if (on && brightness != light.getBrightness()) {
                    light.setBrightness(brightness);
                }
            }
        }
    }

    private List<Device> getLightsContainingName(Device[] devices, String name) {
        List<Device> lights = new ArrayList<>();

        for(Device device : devices) {
            if (device.isLight()) {
                if (device.getName().contains(name)) {
                    lights.add(device);
                }
            }
        }
        return lights;
    }


}
