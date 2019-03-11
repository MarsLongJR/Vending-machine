package com.xbz.vpase.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.web.PortResolver;
import org.springframework.security.web.savedrequest.SavedCookie;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 自定义的请求缓存对象，可以修改requestURI，以便ajax请求时修改登录完成后的跳转页面
 */
public class DefaultSavedRequest implements SavedRequest {
    protected static final Log logger = LogFactory.getLog(org.springframework.security.web.savedrequest.DefaultSavedRequest.class);
    private static final String HEADER_IF_NONE_MATCH = "If-None-Match";
    private static final String HEADER_IF_MODIFIED_SINCE = "If-Modified-Since";
    private final ArrayList<SavedCookie> cookies;
    private final ArrayList<Locale> locales;
    private final Map<String, List<String>> headers;
    private final Map<String, String[]> parameters;
    private final String contextPath;
    private final String method;
    private final String pathInfo;
    private final String queryString;
    private  String requestURI;
    private final String requestURL;
    private final String scheme;
    private final String serverName;
    private final String servletPath;
    private final int serverPort;

    public DefaultSavedRequest(HttpServletRequest request, PortResolver portResolver) {
        this.cookies = new ArrayList();
        this.locales = new ArrayList();
        this.headers = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        this.parameters = new TreeMap();
        Assert.notNull(request, "Request required");
        Assert.notNull(portResolver, "PortResolver required");
        this.addCookies(request.getCookies());
        Enumeration names = request.getHeaderNames();

        while(true) {
            String name;
            do {
                do {
                    if(!names.hasMoreElements()) {
                        this.addLocales(request.getLocales());
                        this.addParameters(request.getParameterMap());
                        this.method = request.getMethod();
                        this.pathInfo = request.getPathInfo();
                        this.queryString = request.getQueryString();
                        this.requestURI = request.getRequestURI();
                        this.serverPort = portResolver.getServerPort(request);
                        this.requestURL = request.getRequestURL().toString();
                        this.scheme = request.getScheme();
                        this.serverName = request.getServerName();
                        this.contextPath = request.getContextPath();
                        this.servletPath = request.getServletPath();
                        return;
                    }

                    name = (String)names.nextElement();
                } while("If-Modified-Since".equalsIgnoreCase(name));
            } while("If-None-Match".equalsIgnoreCase(name));

            Enumeration values = request.getHeaders(name);

            while(values.hasMoreElements()) {
                this.addHeader(name, (String)values.nextElement());
            }
        }
    }

    private DefaultSavedRequest(Builder builder) {
        this.cookies = new ArrayList();
        this.locales = new ArrayList();
        this.headers = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        this.parameters = new TreeMap();
        this.contextPath = builder.contextPath;
        this.method = builder.method;
        this.pathInfo = builder.pathInfo;
        this.queryString = builder.queryString;
        this.requestURI = builder.requestURI;
        this.requestURL = builder.requestURL;
        this.scheme = builder.scheme;
        this.serverName = builder.serverName;
        this.servletPath = builder.servletPath;
        this.serverPort = builder.serverPort;
    }

    private void addCookies(Cookie[] cookies) {
        if(cookies != null) {
            Cookie[] var2 = cookies;
            int var3 = cookies.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                Cookie cookie = var2[var4];
                this.addCookie(cookie);
            }
        }

    }

    private void addCookie(Cookie cookie) {
        this.cookies.add(new SavedCookie(cookie));
    }

    private void addHeader(String name, String value) {
        List<String> values = (List)this.headers.get(name);
        if(values == null) {
            values = new ArrayList();
            this.headers.put(name, values);
        }

        ((List)values).add(value);
    }

    private void addLocales(Enumeration<Locale> locales) {
        while(locales.hasMoreElements()) {
            Locale locale = (Locale)locales.nextElement();
            this.addLocale(locale);
        }

    }

    private void addLocale(Locale locale) {
        this.locales.add(locale);
    }

    private void addParameters(Map<String, String[]> parameters) {
        if(!ObjectUtils.isEmpty(parameters)) {
            Iterator var2 = parameters.keySet().iterator();

            while(var2.hasNext()) {
                String paramName = (String)var2.next();
                Object paramValues = parameters.get(paramName);
                if(paramValues instanceof String[]) {
                    this.addParameter(paramName, (String[])((String[])paramValues));
                } else if(logger.isWarnEnabled()) {
                    logger.warn("ServletRequest.getParameterMap() returned non-String array");
                }
            }
        }

    }

    private void addParameter(String name, String[] values) {
        this.parameters.put(name, values);
    }

    public boolean doesRequestMatch(HttpServletRequest request, PortResolver portResolver) {
        return !this.propertyEquals("pathInfo", this.pathInfo, request.getPathInfo())?false:(!this.propertyEquals("queryString", this.queryString, request.getQueryString())?false:(!this.propertyEquals("requestURI", this.requestURI, request.getRequestURI())?false:(!"GET".equals(request.getMethod()) && "GET".equals(this.method)?false:(!this.propertyEquals("serverPort", Integer.valueOf(this.serverPort), Integer.valueOf(portResolver.getServerPort(request)))?false:(!this.propertyEquals("requestURL", this.requestURL, request.getRequestURL().toString())?false:(!this.propertyEquals("scheme", this.scheme, request.getScheme())?false:(!this.propertyEquals("serverName", this.serverName, request.getServerName())?false:(!this.propertyEquals("contextPath", this.contextPath, request.getContextPath())?false:this.propertyEquals("servletPath", this.servletPath, request.getServletPath())))))))));
    }

    public String getContextPath() {
        return this.contextPath;
    }

    public List<Cookie> getCookies() {
        ArrayList cookieList = new ArrayList(this.cookies.size());
        Iterator var2 = this.cookies.iterator();

        while(var2.hasNext()) {
            SavedCookie savedCookie = (SavedCookie)var2.next();
            cookieList.add(savedCookie.getCookie());
        }

        return cookieList;
    }

    public String getRedirectUrl() {
        return UrlUtils.buildFullRequestUrl(this.scheme, this.serverName, this.serverPort, this.requestURI, this.queryString);
    }

    public Collection<String> getHeaderNames() {
        return this.headers.keySet();
    }

    public List<String> getHeaderValues(String name) {
        List values = (List)this.headers.get(name);
        return values == null?Collections.emptyList():values;
    }

    public List<Locale> getLocales() {
        return this.locales;
    }

    public String getMethod() {
        return this.method;
    }

    public Map<String, String[]> getParameterMap() {
        return this.parameters;
    }

    public Collection<String> getParameterNames() {
        return this.parameters.keySet();
    }

    public String[] getParameterValues(String name) {
        return (String[])this.parameters.get(name);
    }

    public String getPathInfo() {
        return this.pathInfo;
    }

    public String getQueryString() {
        return this.queryString;
    }

    public String getRequestURI() {
        return this.requestURI;
    }

    public String getRequestURL() {
        return this.requestURL;
    }

    public String getScheme() {
        return this.scheme;
    }

    public String getServerName() {
        return this.serverName;
    }

    public int getServerPort() {
        return this.serverPort;
    }

    public String getServletPath() {
        return this.servletPath;
    }

    private boolean propertyEquals(String log, Object arg1, Object arg2) {
        if(arg1 == null && arg2 == null) {
            if(logger.isDebugEnabled()) {
                logger.debug(log + ": both null (property equals)");
            }

            return true;
        } else if(arg1 != null && arg2 != null) {
            if(arg1.equals(arg2)) {
                if(logger.isDebugEnabled()) {
                    logger.debug(log + ": arg1=" + arg1 + "; arg2=" + arg2 + " (property equals)");
                }

                return true;
            } else {
                if(logger.isDebugEnabled()) {
                    logger.debug(log + ": arg1=" + arg1 + "; arg2=" + arg2 + " (property not equals)");
                }

                return false;
            }
        } else {
            if(logger.isDebugEnabled()) {
                logger.debug(log + ": arg1=" + arg1 + "; arg2=" + arg2 + " (property not equals)");
            }

            return false;
        }
    }

    public String toString() {
        return "DefaultSavedRequest[" + this.getRedirectUrl() + "]";
    }

    @JsonIgnoreProperties(
            ignoreUnknown = true
    )
    @JsonPOJOBuilder(
            withPrefix = "set"
    )
    public static class Builder {
        private List<SavedCookie> cookies = null;
        private List<Locale> locales = null;
        private Map<String, List<String>> headers;
        private Map<String, String[]> parameters;
        private String contextPath;
        private String method;
        private String pathInfo;
        private String queryString;
        private String requestURI;
        private String requestURL;
        private String scheme;
        private String serverName;
        private String servletPath;
        private int serverPort;

        public Builder() {
            this.headers = new TreeMap(String.CASE_INSENSITIVE_ORDER);
            this.parameters = new TreeMap();
            this.serverPort = 80;
        }

        public Builder setCookies(List<SavedCookie> cookies) {
            this.cookies = cookies;
            return this;
        }

        public Builder setLocales(List<Locale> locales) {
            this.locales = locales;
            return this;
        }

        public Builder setHeaders(Map<String, List<String>> header) {
            this.headers.putAll(header);
            return this;
        }

        public Builder setParameters(Map<String, String[]> parameters) {
            this.parameters = parameters;
            return this;
        }

        public Builder setContextPath(String contextPath) {
            this.contextPath = contextPath;
            return this;
        }

        public Builder setMethod(String method) {
            this.method = method;
            return this;
        }

        public Builder setPathInfo(String pathInfo) {
            this.pathInfo = pathInfo;
            return this;
        }

        public Builder setQueryString(String queryString) {
            this.queryString = queryString;
            return this;
        }

        public Builder setRequestURI(String requestURI) {
            this.requestURI = requestURI;
            return this;
        }

        public Builder setRequestURL(String requestURL) {
            this.requestURL = requestURL;
            return this;
        }

        public Builder setScheme(String scheme) {
            this.scheme = scheme;
            return this;
        }

        public Builder setServerName(String serverName) {
            this.serverName = serverName;
            return this;
        }

        public Builder setServletPath(String servletPath) {
            this.servletPath = servletPath;
            return this;
        }

        public Builder setServerPort(int serverPort) {
            this.serverPort = serverPort;
            return this;
        }

        public DefaultSavedRequest build() {
            DefaultSavedRequest savedRequest = new DefaultSavedRequest(this);
            if(!ObjectUtils.isEmpty(this.cookies)) {
                Iterator var2 = this.cookies.iterator();

                while(var2.hasNext()) {
                    SavedCookie cookie = (SavedCookie)var2.next();
                    savedRequest.addCookie(cookie.getCookie());
                }
            }

            if(!ObjectUtils.isEmpty(this.locales)) {
                savedRequest.locales.addAll(this.locales);
            }

            savedRequest.addParameters(this.parameters);
            this.headers.remove("If-Modified-Since");
            this.headers.remove("If-None-Match");
            if(!ObjectUtils.isEmpty(this.headers)) {
                savedRequest.headers.putAll(this.headers);
            }

            return savedRequest;
        }
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }
}
