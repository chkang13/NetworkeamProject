import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
 
public class fineDust {

    public static void main(String[] args) {
        // �̼����� �溸 ���� ��ȸ ���� - �ѱ�ȯ����� api ����
        try {
            // ����Ű
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
            // JSON parser ����� ���ڿ� �����͸� ��üȭ�Ѵ�.
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject)parser.parse(result);
            
            // list �Ʒ��� �迭���·�
            // {"list" : [ {"returnType":"json","clearDate":"--",.......} ] 
            JSONArray parse_listArr = (JSONArray)obj.get("list");
            
            String miseType = "";
            
            // ��ü���·�
            // {"returnType":"json","clearDate":"--",.......},... 
            int p=0;
            for (int i=0;i< parse_listArr.size();i++) {
                JSONObject weather = (JSONObject) parse_listArr.get(i);
                String dataDate = (String) weather.get("dataDate");            // �߷ɳ�¥
                String districtName = (String) weather.get("districtName");    // �߷�����
                String moveName = (String) weather.get("moveName");            // �߷ɱǿ�
                String issueDate = (String) weather.get("issueDate");        // �߷�����
                String issueTime = (String) weather.get("issueTime");        // �߷ɽð�
                String issueVal  = (String) weather.get("issueVal");        // �߷ɳ�
                String itemCode  = (String) weather.get("itemCode");        // �̼����� ���� PM10, PM25
                String issueGbn  = (String) weather.get("issueGbn");        // �溸�ܰ� : ���Ǻ�/�溸
                String clearDate = (String) weather.get("clearDate");        // ��������
                String clearTime = (String) weather.get("clearTime");        // �����ð�
                String clearVal = (String) weather.get("clearVal");            // ������ �̼�������
                
                if (itemCode.equals("PM10")) {            
                    miseType = "";
                } else if (itemCode.equals("PM25")) {    
                    miseType = "�ʹ̼�����";
                }
                StringBuffer sb = new StringBuffer();
                sb.append("�߷ɳ�¥ : " + dataDate + ", ���� : " + districtName + " ("+ moveName +"), " + "�߷ɽð� : "+ issueDate + " " + issueTime + ", �� : " + issueVal + " ("+ issueGbn +") " + miseType);
               
                System.out.println(sb.toString());

            }

            
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }
 
}