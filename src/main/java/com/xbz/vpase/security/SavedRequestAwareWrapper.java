package com.xbz.vpase.security;

import org.springframework.security.web.savedrequest.Enumerator;
import org.springframework.security.web.savedrequest.FastHttpDateFormat;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 自定义的缓存包裹器，与SpringScurity包下SavedRequestAwareWrapper的类完全相同，只因为SpringScurity的该
 * 类是私有类，无法供自定义的HttpSessionRequestCache类调用
 */
public class SavedRequestAwareWrapper extends HttpServletRequestWrapper {
    protected static final TimeZone GMT_ZONE = TimeZone.getTimeZone("GMT");
    protected static Locale defaultLocale = Locale.getDefault();
    protected SavedRequest savedRequest = null;
    protected final SimpleDateFormat[] formats = new SimpleDateFormat[3];

    public SavedRequestAwareWrapper(SavedRequest saved, HttpServletRequest request) {
        super(request);
        this.savedRequest = saved;
        this.formats[0] = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
        this.formats[1] = new SimpleDateFormat("EEEEEE, dd-MMM-yy HH:mm:ss zzz", Locale.US);
        this.formats[2] = new SimpleDateFormat("EEE MMMM d HH:mm:ss yyyy", Locale.US);
        this.formats[0].setTimeZone(GMT_ZONE);
        this.formats[1].setTimeZone(GMT_ZONE);
        this.formats[2].setTimeZone(GMT_ZONE);
    }

    public long getDateHeader(String name) {
        String value = this.getHeader(name);
        if(value == null) {
            return -1L;
        } else {
            long result = FastHttpDateFormat.parseDate(value, this.formats);
            if(result != -1L) {
                return result;
            } else {
                throw new IllegalArgumentException(value);
            }
        }
    }

    public String getHeader(String name) {
        List values = this.savedRequest.getHeaderValues(name);
        return values.isEmpty()?null:(String)values.get(0);
    }

    public Enumeration getHeaderNames() {
        return new Enumerator(this.savedRequest.getHeaderNames());
    }

    public Enumeration getHeaders(String name) {
        return new Enumerator(this.savedRequest.getHeaderValues(name));
    }

    public int getIntHeader(String name) {
        String value = this.getHeader(name);
        return value == null?-1:Integer.parseInt(value);
    }

    public Locale getLocale() {
        List locales = this.savedRequest.getLocales();
        return locales.isEmpty()?Locale.getDefault():(Locale)locales.get(0);
    }

    public Enumeration getLocales() {
        Object locales = this.savedRequest.getLocales();
        if(((List)locales).isEmpty()) {
            locales = new ArrayList(1);
            ((List)locales).add(Locale.getDefault());
        }

        return new Enumerator((Collection)locales);
    }

    public String getMethod() {
        return this.savedRequest.getMethod();
    }

    public String getParameter(String name) {
        String value = super.getParameter(name);
        if(value != null) {
            return value;
        } else {
            String[] values = this.savedRequest.getParameterValues(name);
            return values != null && values.length != 0?values[0]:null;
        }
    }

    public Map getParameterMap() {
        Set names = this.getCombinedParameterNames();
        HashMap parameterMap = new HashMap(names.size());
        Iterator var3 = names.iterator();

        while(var3.hasNext()) {
            String name = (String)var3.next();
            parameterMap.put(name, this.getParameterValues(name));
        }

        return parameterMap;
    }

    private Set<String> getCombinedParameterNames() {
        HashSet names = new HashSet();
        names.addAll(super.getParameterMap().keySet());
        names.addAll(this.savedRequest.getParameterMap().keySet());
        return names;
    }

    public Enumeration getParameterNames() {
        return new Enumerator(this.getCombinedParameterNames());
    }

    public String[] getParameterValues(String name) {
        String[] savedRequestParams = this.savedRequest.getParameterValues(name);
        String[] wrappedRequestParams = super.getParameterValues(name);
        if(savedRequestParams == null) {
            return wrappedRequestParams;
        } else if(wrappedRequestParams == null) {
            return savedRequestParams;
        } else {
            List wrappedParamsList = Arrays.asList(wrappedRequestParams);
            ArrayList combinedParams = new ArrayList(wrappedParamsList);
            String[] var6 = savedRequestParams;
            int var7 = savedRequestParams.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                String savedRequestParam = var6[var8];
                if(!wrappedParamsList.contains(savedRequestParam)) {
                    combinedParams.add(savedRequestParam);
                }
            }

            return (String[])combinedParams.toArray(new String[combinedParams.size()]);
        }
    }
}
