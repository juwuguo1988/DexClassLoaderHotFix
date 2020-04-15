package cn.whm.hot.impl;

/**
 * Created by juwuguo on 2020-04-14.
 */
public class ActionException implements IAction {
    @Override
    public String doAction() {
        return "something wrong here!!!";
    }
}
