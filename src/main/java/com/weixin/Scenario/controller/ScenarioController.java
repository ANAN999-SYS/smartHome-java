package com.weixin.Scenario.controller;


import com.weixin.Scenario.entity.Scenario;
import com.weixin.Scenario.service.IScenarioService;
import com.weixin.Utils.JWTUtils;
import com.weixin.commons.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 纸凤孤凰
 * @since 2024-04-05
 */
@CrossOrigin
@RestController
@RequestMapping("/Scenario")
public class ScenarioController {
    @Autowired
    private IScenarioService iScenarioService;

    @GetMapping("/getScenario")
    public Result getScenarioData(@RequestHeader("Authorization") String token){
        String openId= JWTUtils.validateToken(token);
        Result result=iScenarioService.getScenarioData(openId);
        return result;
    }

    @GetMapping("/getScenarioBySid")
    public Result getScenarioBySid(@RequestHeader("Authorization") String token,@RequestParam String sId){
        String openId= JWTUtils.validateToken(token);
        Result result=iScenarioService.getScenarioBySid(openId,sId);
        return result;
    }

    @PostMapping("/addScenario")
    public Result addScenario(@RequestBody Scenario scenario){
        Result result=iScenarioService.addScenario(scenario);
        return result;
    }

    @PostMapping("/editScenario")
    public Result editScenario(@RequestBody Scenario scenario){
        Result result=iScenarioService.editScenario(scenario);
        return result;
    }

    @PostMapping("/deleteScenario")
    public Result deleteScenario(@RequestBody Scenario scenario){
        Result result=iScenarioService.deleteScenario(scenario);
        return result;
    }

    @PostMapping("/ctrlScenario")
    public Result ctrlScenario(@RequestBody Scenario scenario){
        Result result=iScenarioService.ctrlScenario(scenario);
        return result;
    }
    //后台

    @GetMapping("/getScenarioAll")
    public Result getScenarioAll(){
        Result result=iScenarioService.getScenarioAll();
        return result;
    }

    @PostMapping("/deleteScenarioData")
    public Result deleteScenarioData(@RequestBody Scenario scenario){
        Result result=iScenarioService.deleteScenarioData(scenario);
        return result;
    }


    @PostMapping("/deleteScenarios")
    public Result deleteScenarios(@RequestBody List<Scenario> scenarioList){
        Result result=iScenarioService.deleteScenarios(scenarioList);
        return result;
    }
}

