package com.weixin.Scenario.service;

import com.weixin.Scenario.entity.Scenario;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weixin.commons.Result;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 纸凤孤凰
 * @since 2024-04-05
 */
public interface IScenarioService extends IService<Scenario> {
    Result getScenarioData(String openId);

    Result getScenarioBySid(String openId,String sId);

    Result addScenario(Scenario scenario);

    Result editScenario(Scenario scenario);

    Result deleteScenarioData(Scenario scenario);

    Result ctrlScenario(Scenario scenario);

    //后台
    Result getScenarioAll();
    Result deleteScenario(Scenario scenario);

    Result deleteScenarios(List<Scenario> scenarioList);
}
