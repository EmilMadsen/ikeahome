package dk.emad.ikeahome.web;

import dk.emad.ikeahome.service.DeviceService;
import nl.stijngroenen.tradfri.device.Device;
import nl.stijngroenen.tradfri.device.Light;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DeviceController {
    private static final Logger log = LoggerFactory.getLogger(DeviceController.class);

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("/id/{id}")
    public Device getById(@PathVariable int id) {
        log.info("getById() - {}", id);
        return deviceService.getById(id);
    }

    @GetMapping("/id/{id}/on/{on}/brightness/{brightness}")
    public Light setById(@PathVariable int id, @PathVariable boolean on, @PathVariable int brightness) {
        log.info("setById() - id: {} - on: {} - brightness: {}", id, on, brightness);
        return deviceService.setById(id, on, brightness);
    }

    @GetMapping("/name/{name}")
    public List<Device> getByName(@PathVariable String name) {
        log.info("getByName() - {}", name);
        return deviceService.getByName(name);
    }

    @GetMapping("/name/{name}/on/{on}/brightness/{brightness}")
    public List<Device> setByName(@PathVariable String name, @PathVariable boolean on, @PathVariable int brightness) {
        log.info("setByName() - name: {} - on: {} - brightness: {}", name, on, brightness);
        return deviceService.setByName(name, on, brightness);
    }

    @GetMapping("/all")
    public Device[] getAll() {
        log.info("getAll()");
        return deviceService.getAll();
    }

    @GetMapping("/all/on/{on}/brightness/{brightness}")
    public Device[] setAll(@PathVariable boolean on, @PathVariable int brightness) {
        log.info("setAll() - on: {} - brightness: {}", on, brightness);
        return deviceService.setAll(on, brightness);
    }

    @GetMapping("/battery")
    public Map<String, Integer> getBatteryStatus() {
        log.info("getBatteryStatus()");
        return deviceService.getBatteryStatus();
    }

}
