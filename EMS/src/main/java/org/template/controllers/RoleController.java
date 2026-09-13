package org.template.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.template.common.controllers.BaseController;
import org.template.common.models.ResponseObject;
import org.template.common.services.ObjectService;
import org.template.managers.RoleManager;
import org.template.models.DO.RoleDO;
import org.template.models.RO.RoleRequestObject;
import org.template.models.VO.RoleVO;

@RestController
@RequestMapping("/admin/role")
public class RoleController extends BaseController {

    private final RoleManager roleManager;

    public RoleController(RoleManager roleManager) {
        this.roleManager = roleManager;
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseObject> getAll() {

        return ObjectService.getResponseBody(roleManager.getAllRoles());

    }

    @PostMapping("/save")
    public ResponseEntity<ResponseObject> save(@RequestBody RoleRequestObject roleRequestObject) {

        RoleDO roleDO = new RoleDO();

        ObjectService.copyProperties(roleRequestObject, roleDO);

        RoleVO roleVO = new RoleVO(roleDO);

        roleManager.saveRole(roleVO);

        return ObjectService.getResponseBody(roleVO);
    }

}
