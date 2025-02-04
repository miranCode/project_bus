package org.zerock.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

@Controller
public class BusController {

    private Map<String, String> busRoutesMap;
    
    private Map<String, String> stationMap = new HashMap<>();

    // JSON 파일을 읽어 정류장 명칭과 ID 매핑
    public void loadStationNames() throws Exception {
        ClassPathResource resource = new ClassPathResource("data/stationNames.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        reader.close();

        // JSON 파싱
        JSONObject jsonObject = new JSONObject(stringBuilder.toString());
        JSONArray data = jsonObject.getJSONArray("DATA");

        // crtr_nm을 키로, crtr_id를 값으로 맵핑
        for (int i = 0; i < data.length(); i++) {
            JSONObject station = data.getJSONObject(i);
            String crtrNm = station.getString("crtr_nm");  // 정류장 명칭
            String crtrId = String.valueOf(station.getInt("crtr_id"));  // 정류장 ID
            stationMap.put(crtrNm, crtrId);
        }
    }
    
    // JSON 파일을 읽어 매핑하는 메서드
    public void loadBusRoutes() throws Exception {
        busRoutesMap = new HashMap<>();
        
        // ClassPathResource를 사용하여 resources 폴더 안의 JSON 파일을 읽음
        ClassPathResource resource = new ClassPathResource("data/busRoutes.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
        
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        reader.close();
        
        // JSON 파싱
        JSONObject jsonObject = new JSONObject(stringBuilder.toString());
        JSONArray data = jsonObject.getJSONArray("DATA");
        
        // rte_nm을 키로, rte_id를 값으로 맵핑
        for (int i = 0; i < data.length(); i++) {
            JSONObject busRoute = data.getJSONObject(i);
            String rteNm = busRoute.getString("rte_nm");
            String rteId = busRoute.getString("rte_id");
            busRoutesMap.put(rteNm, rteId);
        }
    }

    @GetMapping("/busArrival")
    public String showBusArrivalPage() {
        return "user/businfo/busArrival"; // busArrival.jsp를 반환
    }
    
    @GetMapping("/busArrivalByStation")
    public String showBusArrivalPage2() {
        return "user/businfo/busArrivalByStation"; // busArrivalByStation.jsp로 반환
    }

    @GetMapping("/getBusArrivalInfo")
    public String getBusArrivalInfo(@RequestParam String rteNm, Model model) throws Exception {
        // JSON 파일에서 버스 노선 정보 로드
        loadBusRoutes();

        // 사용자가 입력한 rteNm에 해당하는 rte_id 찾기
        String busRouteId = busRoutesMap.get(rteNm);
        if (busRouteId == null) {
            model.addAttribute("error", "잘못된 버스 노선명입니다.");
            return "user/businfo/busArrival"; // 오류 메시지 표시
        }

        // API 호출을 위한 URL 설정
        StringBuilder urlBuilder = new StringBuilder("http://ws.bus.go.kr/api/rest/arrive/getArrInfoByRouteAll");
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=KYf4A8NFOMfwwW7YJje7YCoLqgJxcMt%2Fnq11fpMt9XNSVCy6IXwUS0GwNGxaWUfr1%2FkRTNREjArXiHv5xglkkg%3D%3D"); /* Service Key */
        urlBuilder.append("&" + URLEncoder.encode("busRouteId", "UTF-8") + "=" + URLEncoder.encode(busRouteId, "UTF-8")); /* 노선ID */
        
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/xml");

        BufferedReader rd;
        StringBuilder sb = new StringBuilder();
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        // 응답 XML 출력 (디버깅용)
        System.out.println("XML Response: " + sb.toString());

        // XML 파싱
        String responseXml = sb.toString();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(responseXml)));

        // XPath로 stNm, arrmsg1, exps1 추출
        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "//itemList";  // itemList 내의 모든 요소 선택
        NodeList nodeList = (NodeList) xPath.evaluate(expression, document, XPathConstants.NODESET);

        List<String> stNmList = new ArrayList<>();  // stNm을 저장할 리스트
        List<String> arrivalStatusList = new ArrayList<>();  // "곧 도착" 여부를 저장할 리스트
        List<String> exps1List = new ArrayList<>();  // exps1 값을 저장할 리스트
        
        // NodeList에서 stNm, arrmsg1, exps1 값을 모두 추출
        for (int i = 0; i < nodeList.getLength(); i++) {
            String stNm = xPath.evaluate("stNm", nodeList.item(i)); // 정류소 이름
            String arrmsg1 = xPath.evaluate("arrmsg1", nodeList.item(i)); // 도착 메시지
            String exps1 = xPath.evaluate("exps1", nodeList.item(i)); // 지수평활 도착 예정 시간 (초)

            // 초를 분으로 변환
            int exps1InSeconds = Integer.parseInt(exps1);  // exps1 값을 초로 변환
            int exps1InMinutes = exps1InSeconds / 60;  // 초를 분으로 변환

            stNmList.add(stNm);
            exps1List.add(String.valueOf(exps1InMinutes));  // 분으로 변환된 값을 리스트에 추가

            // arrmsg1이 '곧 도착'일 때 "곧 도착"을 추가
            if ("곧 도착".equals(arrmsg1)) {
                arrivalStatusList.add("곧 도착");
            } else {
                arrivalStatusList.add("");  // 그 외의 경우는 빈 문자열
            }
        }

        // 모델에 stNmList, arrivalStatusList, exps1List 추가
        model.addAttribute("rteNm", rteNm); // 버스 번호 
        model.addAttribute("stNmList", stNmList);
        model.addAttribute("arrivalStatusList", arrivalStatusList);
        model.addAttribute("exps1List", exps1List);  // 분으로 변환된 exps1 리스트도 추가

        System.out.println("rteNm : " + rteNm);
        System.out.println("stNmList : " + stNmList);
        System.out.println("arrivalStatusList : " + arrivalStatusList);
        System.out.println("exps1List : " + exps1List);  // exps1 리스트 출력

        return "user/businfo/busArrival"; // busArrival.jsp로 결과를 반환
    }
    
    @GetMapping("/getBusArrivalByStationInfo")
    public String getBusArrivalByStationInfo(@RequestParam String stNm, Model model) throws Exception {
        // 정류장 명칭으로 정류장 ID 찾기
        loadStationNames();
        String stId = stationMap.get(stNm);

        if (stId == null) {
            model.addAttribute("error", "정류장 명칭을 잘못 입력하셨습니다.");
            return "user/businfo/busArrivalByStation"; // 오류 메시지 표시
        }

        // API 호출을 위한 URL 설정
        StringBuilder urlBuilder = new StringBuilder("http://ws.bus.go.kr/api/rest/arrive/getLowArrInfoByStId");
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=KYf4A8NFOMfwwW7YJje7YCoLqgJxcMt%2Fnq11fpMt9XNSVCy6IXwUS0GwNGxaWUfr1%2FkRTNREjArXiHv5xglkkg%3D%3D"); // Service Key
        urlBuilder.append("&" + URLEncoder.encode("stId", "UTF-8") + "=" + URLEncoder.encode(stId, "UTF-8")); // 정류소 ID

        // API 요청
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/xml");

        BufferedReader rd;
        StringBuilder sb = new StringBuilder();
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        // 응답 데이터 파싱 (XML 처리)
        String response = sb.toString();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(response));
        Document doc = builder.parse(is);

        // XPath로 필요한 데이터 추출
        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "//itemList";  // itemList 항목을 찾아서
        NodeList nodeList = (NodeList) xPath.evaluate(expression, doc, XPathConstants.NODESET);

        // 필요한 정보를 저장할 리스트들
        List<String> busRouteAbrvList = new ArrayList<>();
        List<String> arrmsg1List = new ArrayList<>();
        List<String> stNmList = new ArrayList<>();
        List<String> exps1List = new ArrayList<>();

        // XML 노드에서 필요한 정보 추출
        for (int i = 0; i < nodeList.getLength(); i++) {
            String busRouteAbrv = xPath.evaluate("busRouteAbrv", nodeList.item(i));  // 노선명
            String arrmsg1 = xPath.evaluate("arrmsg1", nodeList.item(i));  // 첫번째 도착 예고 메시지
            String stNm1 = xPath.evaluate("stNm", nodeList.item(i));  // 정류소명
            String exps1 = xPath.evaluate("exps1", nodeList.item(i));  // 도착 예정 시간 (초)

            // 초를 분으로 변환
            int exps1InSeconds = Integer.parseInt(exps1);
            int exps1InMinutes = exps1InSeconds / 60;

            busRouteAbrvList.add(busRouteAbrv);
            arrmsg1List.add(arrmsg1);
            stNmList.add(stNm1);
            exps1List.add(String.valueOf(exps1InMinutes));  // 분으로 변환하여 리스트에 추가
        }

        // 모델에 데이터 추가
        model.addAttribute("stNm", stNm);
        model.addAttribute("busRouteAbrvList", busRouteAbrvList);
        model.addAttribute("arrmsg1List", arrmsg1List);
        model.addAttribute("stNmList", stNmList);
        model.addAttribute("exps1List", exps1List);

        return "user/businfo/busArrivalByStation";  // busArrivalByStation.jsp로 반환
    }
}
