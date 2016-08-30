package com.flux.kituri.app.flux;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * BaseActionsCreator
 * 业务逻辑请务必继承此模块
 */
public class ActionsCreator {

    //private static ActionsCreator instance;
    protected final Dispatcher dispatcher;

    protected ActionsCreator(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public static ActionsCreator get(Dispatcher dispatcher, Class<?> actionsCreatorType) {
        ActionsCreator instance = null;
        Constructor constructor = null;
        try {
            constructor = actionsCreatorType.getDeclaredConstructor(Dispatcher.class);
            instance = (ActionsCreator) constructor.newInstance(dispatcher);
        } catch (NoSuchMethodException e) {
            return null;
        } catch (InstantiationException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        } catch (InvocationTargetException e) {
            return null;
        }

        return instance;
    }
}
