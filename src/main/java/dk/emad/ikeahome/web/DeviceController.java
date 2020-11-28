package dk.emad.ikeahome.web;

import dk.emad.ikeahome.service.DeviceService;
import nl.stijngroenen.tradfri.device.Device;
import nl.stijngroenen.tradfri.device.Light;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("/id/{id}")
    public Device getById(@PathVariable int id) {
        return deviceService.getById(id);
    }

    @GetMapping("/id/{id}/on/{on}/brightness/{brightness}")
    public Light setById(@PathVariable int id, @PathVariable boolean on, @PathVariable int brightness) {
        return deviceService.setById(id, on, brightness);
    }



    @GetMapping("/name/{name}")
    public List<Device> getByName(@PathVariable String name) {
        return deviceService.getByName(name);
    }

    @GetMapping("/name/{name}/on/{on}/brightness/{brightness}")
    public List<Device> setByName(@PathVariable String name, @PathVariable boolean on, @PathVariable int brightness) {
        return deviceService.setByName(name, on, brightness);
    }



    @GetMapping("/all")
    public Device[] getAll() {
        return deviceService.getAll();
    }

    @GetMapping("/all/on/{on}/brightness/{brightness}")
    public Device[] setAll(@PathVariable boolean on, @PathVariable int brightness) {
        return deviceService.setAll(on, brightness);
    }

}
