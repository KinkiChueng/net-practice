package URLConnection;

import java.util.Date;
import java.util.Locale;

/**
 * 7-6 解析和查询cache-control首部
 * Created by Administrator on 2018/1/21.
 */
public class CacheControl {
    private Date maxAge = null;
    private Date sMaxAge = null;            //从现在起到缓存项在共享缓存中过期前的秒数，私有缓存可以将缓存项保留更长时间
    private boolean mustRevalidate = false; //使得继续生效
    private boolean noCache = false;        //缓存项仍可以缓存，不过客户端在每次访问时要用一个Etag或Last-modified首部验证响应状态
    private boolean noStore = false;        //不管怎样都不缓存
    private boolean proxyRevalidate = false;
    private boolean publicCache = false;
    private boolean privateCache = false;

    public CacheControl(String s) {
        if (s == null || !s.contains(":")) {
            return; //默认策略
        }
        String value = s.split(":")[1].trim();
        String[] components = value.split(",");

        Date now = new Date();
        for (String component : components) {
            component = component.trim().toLowerCase(Locale.CHINA);
            if (component.startsWith("max-age=")) {
                int secondInTheFuture = Integer.parseInt(component.substring(8));
                maxAge = new Date(now.getTime() + 1000 * secondInTheFuture);
            } else if (component.startsWith("s-maxage=")) {
                int secondsInFuture = Integer.parseInt(component.substring(8));
                sMaxAge = new Date(now.getTime() + 1000 * secondsInFuture);
            } else if (component.equals("must-revalidate")) {
                mustRevalidate = true;
            } else if (component.equals("proxy-revalidate")) {
                proxyRevalidate = true;
            } else if (component.equals("no-cache")) {
                noCache = true;
            } else if (component.equals("public")) {
                publicCache = true;
            } else if (component.equals("private")) {
                privateCache = true;
            }
        }
    }

    public boolean privateCache() {
        return privateCache;
    }

    public Date getMaxAge() {
        return maxAge;
    }

    public Date getsMaxAge() {
        return sMaxAge;
    }

    public boolean mustRevalidate() {
        return mustRevalidate;
    }

    public boolean noCache() {
        return noCache;
    }

    public boolean noStore() {
        return noStore;
    }

    public boolean proxyRevalidate() {
        return proxyRevalidate;
    }

    public boolean publicCache() {
        return publicCache;
    }
}
