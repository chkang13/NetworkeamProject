import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
 
public class fineDust {

    public static void main(String[] args) {
        // 미세먼지 경보 정보 조회 서비스 - 한국환경공단 api 서비스
        try {
            // 인증키
            String serviceKey = "CzYhQo4IxF3EOauiocZ0%2B21CO9pHEmDOzvaY3CdyFuswL%2BjedKxQiTMDM9AcaYHxPKDfUuTTthVEtwebQAimPQ%3D%3D";
            
            String urlStr = "http://openapi.airkorea.or.kr/openapi/services/rest/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo";
            urlStr += "?"+ URLEncoder.encode("ServiceKey","UTF-8") +"=" + serviceKey;
            urlStr += "&"+ URLEncoder.encode("numOfRows","UTF-8") +"=200";
            urlStr += "&"+ URLEncoder.encode("pageNo","UTF-8") +"=1";
            urlStr += "&"+ URLEncoder.encode("year","UTF-8") +"=2020";
            urlStr += "&"+ URLEncoder.encode("_returnType","UTF-8") +"=json";
            
            URL url = new URL(urlStr);
            
            String line = "";
            String result = "";
            
            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
            while ((line = br.readLine()) != null) {
                result = result.concat(line);
                //System.out.println(line);                
            }      
            // JSON parser 만들어 문자열 데이터를 객체화한다.
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject)parser.parse(result);
            
            // list 아래가 배열형태로
            // {"list" : [ {"returnType":"json","clearDate":"--",.......} ] 
            JSONArray parse_listArr = (JSONArray)obj.get("list");
            
            String miseType = "";
            
            // 객체형태로
            // {"returnType":"json","clearDate":"--",.......},... 
            int p=0;
            for (int i=0;i< parse_listArr.size();i++) {
                JSONObject weather = (JSONObject) parse_listArr.get(i);
                String dataDate = (String) weather.get("dataDate");            // 발령날짜
                String districtName = (String) weather.get("districtName");    // 발령지역
                String moveName = (String) weather.get("moveName");            // 발령권역
                String issueDate = (String) weather.get("issueDate");        // 발령일자
                String issueTime = (String) weather.get("issueTime");        // 발령시간
                String issueVal  = (String) weather.get("issueVal");        // 발령농도
                String itemCode  = (String) weather.get("itemCode");        // 미세먼지 구분 PM10, PM25
                String issueGbn  = (String) weather.get("issueGbn");        // 경보단계 : 주의보/경보
                String clearDate = (String) weather.get("clearDate");        // 해제일자
                String clearTime = (String) weather.get("clearTime");        // 해제시간
                String clearVal = (String) weather.get("clearVal");            // 해제시 미세먼지농도
                
                if (itemCode.equals("PM10")) {            
                    miseType = "";
                } else if (itemCode.equals("PM25")) {    
                    miseType = "초미세먼지";
                }
                StringBuffer sb = new StringBuffer();
                sb.append("발령날짜 : " + dataDate + ", 지역 : " + districtName + " ("+ moveName +"), " + "발령시간 : "+ issueDate + " " + issueTime + ", 농도 : " + issueVal + " ("+ issueGbn +") " + miseType);
               
                System.out.println(sb.toString());

            }

            
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }
 
}