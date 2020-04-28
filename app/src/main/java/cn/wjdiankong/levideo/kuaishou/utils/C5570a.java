package cn.wjdiankong.levideo.kuaishou.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* renamed from: com.yxcorp.retrofit.f.a */
/* compiled from: kSourceFile */
public final class C5570a {
    /* renamed from: a */
//    public static void m15540a(Map<String, String> map, Map<String, String> map2) {
//        if (map != null) {
//            if (map2 != null) {
//                ImmutableSet copyOf = ImmutableSet.copyOf(Sets.a(map.keySet(), map2.keySet()));
//                if (!a.a || copyOf.isEmpty()) {
//                    map.keySet().removeAll(copyOf);
//                } else {
//                    throw new RuntimeException("urlParams和bodyParams有重复字段 （" + TextUtils.join(",", copyOf) + "），请务必移除，否则server验签可能失败");
//                }
//            }
//            for (String next : map.keySet()) {
//                if (map.get(next) == null) {
//                    map.put(next, "");
//                }
//            }
//        }
//        if (map2 != null) {
//            for (String next2 : map2.keySet()) {
//                if (map2.get(next2) == null) {
//                    map2.put(next2, "");
//                }
//            }
//        }
//    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: b */
    public static List<String> m15541b(Map<String, String> map, Map<String, String> map2) {
        String str;
        String str2;
        ArrayList arrayList = new ArrayList(map.size() + map2.size());
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (true) {
            str = "";
            if (!it.hasNext()) {
                break;
            }
            Map.Entry next = it.next();
            StringBuilder sb = new StringBuilder();
            sb.append((String) next.getKey());
            sb.append("=");
            if (next.getValue() != null) {
                str = (String) next.getValue();
            }
            sb.append(str);
            arrayList.add(sb.toString());
        }
        for (Map.Entry next2 : map2.entrySet()) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append((String) next2.getKey());
            sb2.append("=");
            if (next2.getValue() == null) {
                str2 = str;
            } else {
                str2 = (String) next2.getValue();
            }
            sb2.append(str2);
            arrayList.add(sb2.toString());
        }
        try {
            Collections.sort(arrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}