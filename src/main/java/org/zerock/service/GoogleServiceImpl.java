package org.zerock.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.dto.GoogleOAuthDTO;
import org.zerock.dto.UserDTO;
import org.zerock.mapper.MemberMapper;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
//push
@Service
public class GoogleServiceImpl implements GoogleService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public String getAccessToken(String authorizeCode) {
		System.out.println("�룎�븘媛�?");
	    String accessToken = null;
	    String refreshToken = null;
	    String reqURL = "https://oauth2.googleapis.com/token";
	    
	    GoogleOAuthDTO credentials = memberMapper.getGoogleCredentials();
        String clientId = credentials.getApiname();
        String clientSecret = credentials.getApikey();
	    
	    try {
	        URL url = new URL(reqURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setDoOutput(true);
	        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

	        // 占쎌뒄筌ｏ옙 占쎈솁占쎌뵬沃섎챸苑� 占쎄퐬占쎌젟
	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
	        StringBuilder sb = new StringBuilder();
	        sb.append("grant_type=authorization_code");
	        sb.append("&client_id=").append(clientId); // 占쎄깻占쎌뵬占쎌뵠占쎈섧占쎈뱜 ID
	        sb.append("&client_secret=").append(clientSecret); // 占쎄깻占쎌뵬占쎌뵠占쎈섧占쎈뱜 Secret
	        sb.append("&redirect_uri=http://localhost:8080/google/userinfo"); // �뵳�됰뼄占쎌뵠占쎌젂占쎈뱜 URI
	        sb.append("&code=").append(authorizeCode); // 占쎄텢占쎌뒠占쎌쁽 占쎌뵥筌앾옙 �굜遺얜굡
	        bw.write(sb.toString());
	        bw.flush();

	        int responseCode = conn.getResponseCode();
	        System.out.println("Response Code: " + responseCode);

	        if (responseCode == HttpURLConnection.HTTP_OK) {
	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            String result = "";
	            while ((line = br.readLine()) != null) {
	                result += line;
	            }
	            System.out.println("Response Body: " + result);

	            // JSON 占쎈솁占쎈뼓
	            JsonElement element = JsonParser.parseString(result);
	            if (element.getAsJsonObject().has("access_token")) {
	                accessToken = element.getAsJsonObject().get("access_token").getAsString();
	            }
	            if (element.getAsJsonObject().has("refresh_token")) {
	                refreshToken = element.getAsJsonObject().get("refresh_token").getAsString();
	            }

	            System.out.println("Access Token: " + accessToken);
	            System.out.println("Refresh Token: " + refreshToken);

	            br.close();
	        } else {
	            System.out.println("Error Response Code: " + responseCode);
	        }
	        bw.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    if (accessToken == null) {
	        throw new RuntimeException("Access Token is null. Check the API response.");
	    }
	    return accessToken;
	}

    @Override
    public HashMap<String, Object> getUserInfo(String accessToken) {
        HashMap<String, Object> userInfo = new HashMap<>();
        String reqURL = "https://www.googleapis.com/oauth2/v2/userinfo";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            String result = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            // JSON 占쎈솁占쎈뼓
            JsonElement element = JsonParser.parseString(result);
            JsonObject jsonObject = element.getAsJsonObject();

            // 占쎄텢占쎌뒠占쎌쁽 占쎌젟癰귨옙 占쏙옙占쎌삢
            userInfo.put("id", jsonObject.has("id") ? jsonObject.get("id").getAsString() : "Unknown");
            userInfo.put("email", jsonObject.has("email") ? jsonObject.get("email").getAsString() : "Not Provided");
            userInfo.put("name", jsonObject.has("name") ? jsonObject.get("name").getAsString() : "Unknown");
            userInfo.put("picture", jsonObject.has("picture") ? jsonObject.get("picture").getAsString() : "No Picture");
            // 전화번호 정보 추가
            if (jsonObject.has("phone_number")) {
                userInfo.put("phone_number", jsonObject.get("phone_number").getAsString());
            } else {
                userInfo.put("phone_number", "Not Provided");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userInfo;
    }

    public boolean saveUser(HashMap<String, Object> userInfo) {
        String googleId = (String) userInfo.get("id");
        String email = (String) userInfo.get("email");
        String name = (String) userInfo.get("name");
        String phone_number = (String) userInfo.get("phone_number");

        // DTO 占쎄문占쎄쉐
        //GoogleMemberDTO googledto = new GoogleMemberDTO(googleId, name, email);
        UserDTO memberdto = new UserDTO();
        
        memberdto.setId(googleId); // Google ID�몴占� 占쏙옙占쎌삢
        memberdto.setEmail(email);
        memberdto.setName(name); // 占쎄텢占쎌뒠占쎌쁽 占쎌뵠�뵳占� 占쏙옙占쎌삢
        memberdto.setPhone_number(phone_number); // 占쎌뵠筌롫뗄�뵬 占쏙옙占쎌삢
        // 占쎄텢占쎌뒠占쎌쁽 �넫�굝履� (�뤃�덌옙) 占쏙옙占쎌삢
        // Mapper�몴占� 占쎈꽰占쎈퉸 DB占쎈퓠占쎄퐣 google_id嚥∽옙 占쎄텢占쎌뒠占쎌쁽 鈺곌퀬�돳
        UserDTO existingUser = memberMapper.findUserByGoogleId(googleId);

        if (existingUser != null) {
            if (!existingUser.getIsActive()) {
                // 계정이 비활성화된 경우
                System.out.println("### 계정이 비활성화되었습니다. ###");
                return false;
            }
            return true;
        } else {
            // 신규 사용자라면 저장 (비활성화 체크 필요 없음)
            UserDTO newUser = new UserDTO();
            newUser.setId(googleId);
            newUser.setName((String) userInfo.get("name"));
            newUser.setEmail((String) userInfo.get("email"));
            memberMapper.insertUser(existingUser);
            return true;
        }
    }
    
    @Override
    public UserDTO findUserByGoogleId(String googleId) {
        // DB占쎈퓠占쎄퐣 googleId嚥∽옙 占쎄텢占쎌뒠占쎌쁽 鈺곌퀬�돳
        return memberMapper.findUserByGoogleId(googleId);
    }
}