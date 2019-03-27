package org.jeecgframework.core.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 张代浩
 * @ClassName: ContextHolderUtils
 * @Description: 上下文工具类
 * @date 2012-12-15 下午11:27:39
 */
public class ContextHolderUtils {
    private static final Map<String, HttpSession> SESSION_MAP = new HashMap<String, HttpSession>();

    /**
     * SpringMvc下获取request
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;

    }

    /**
     * SpringMvc下获取session
     *
     * @return
     */
    public static HttpSession getSession() {
        HttpServletRequest request = getRequest();
        String tempSessionId = request.getParameter("sessionId");
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        if (StringUtil.isNotEmpty(tempSessionId) && !tempSessionId.equals(sessionId)) {
            sessionId = tempSessionId;
            if (SESSION_MAP.containsKey(sessionId)) {
                session = SESSION_MAP.get(sessionId);
            }
        }
        if (!SESSION_MAP.containsKey(sessionId)) {
            SESSION_MAP.put(sessionId, session);
        }
        return session;
    }

    public static HttpSession getSession(String sessionId) {
        HttpSession session = SESSION_MAP.get(sessionId);
        return session == null ? getSession() : session;
    }

    public static void removeSession(String sessionId) {
        if (SESSION_MAP.containsKey(sessionId)) {
            SESSION_MAP.remove(sessionId);
        }
    }

}
