package pers.fancy.frame4j.helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import pers.fancy.frame4j.bean.Handler;
import pers.fancy.frame4j.util.ArrayUtil;
import pers.fancy.frame4j.util.CollectionUtil;
import pers.fancy.frame4j.annotation.Action;
import pers.fancy.frame4j.bean.Request;

/**
 * controller helper
 *
 * @author Fancy
 */
public final class ControllerHelper {

    private static final Map<Request, Handler> ACTION_MAP = new HashMap<>();

    static {
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (CollectionUtil.isNotEmpty(controllerClassSet)) {
            for (Class<?> controllerClass : controllerClassSet) {
                Method[] methods = controllerClass.getDeclaredMethods();
                if (ArrayUtil.isNotEmpty(methods)) {
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(Action.class)) {
                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();
                            if (mapping.matches("\\w+:/\\w*")) {
                                String[] array = mapping.split(":");
                                if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                                    String requestMethod = array[0];
                                    String requestPath = array[1];
                                    Request request = new Request(requestMethod, requestPath);
                                    Handler handler = new Handler(controllerClass, method);
                                    ACTION_MAP.put(request, handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取 Handler
     */
    public static Handler getHandler(String requestMethod, String requestPath) {
        Request request = new Request(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }
}
