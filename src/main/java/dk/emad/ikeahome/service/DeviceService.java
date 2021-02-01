package dk.emad.ikeahome.service;

import nl.stijngroenen.tradfri.device.Device;
import nl.stijngroenen.tradfri.device.Gateway;
import nl.stijngroenen.tradfri.device.Light;
import nl.stijngroenen.tradfri.device.Remote;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeviceService {

    private final Gateway gateway;

    public DeviceService(Gateway gateway) {
        this.gateway = gateway;
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
        for (Device device : devices) {
            if (device.isLight()) {
                if (device.getName().contains(name)) {
                    lights.add(device);
                }
            }
        }
        return lights;
    }

    public Map<String, Integer> getBatteryStatus() {
        Map<String, Integer> status = new HashMap<>();
        for (Device device : gateway.getDevices()) {
            if (device.isRemote()) {
                Remote remote = device.toRemote();
                status.put(remote.getName(), remote.getDeviceInfo().getBatteryLevel());
            }
        }
        return status;
    }
}
