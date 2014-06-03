package com.ilona.LeaderBoardsClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class LeaderBoardsClient {

	public static void main(String[] args) throws IOException {
		LeaderBoardsClient client = new LeaderBoardsClient();

		client.sendPost("chess", "Mike", 10);
		client.sendGet("chess");
		client.sendPost("tictactoe", "Kate", 1);
		client.sendPost("tictactoe", "John", 50);
		client.sendGet("tictactoe");
	}

	public void sendPost(String game, String user, int score)
			throws IOException {

		URL url = new URL("http://localhost:8080/LeaderBoards/score");
		// create connection:
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setDoOutput(true);

		//send request:
		OutputStreamWriter writer = new OutputStreamWriter(
				connection.getOutputStream());

		writer.write("{\"game\":\"" + game + "\",\"user\":\"" + user
				+ "\",\"score\":" + score + "}");
		writer.flush();
		writer.close();
		System.out.println(connection.getResponseCode());
	}

	public void sendGet(String game) throws IOException {
		URL url = new URL("http://localhost:8080/LeaderBoards/score/" + game
				+ "");
		// create connection:
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setDoOutput(true);
		String line;
		//get response:
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}

		reader.close();

	}

}
