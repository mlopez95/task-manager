package com.sodep;

import com.sodep.api.beans.TaskRequest;
import com.sodep.entities.Task;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@DataJpaTest
public class TasksManagerApplicationTests {

	private RestTemplate template = new RestTemplate();

	@Test
	public void contextLoads() {
	}

	@Test
	public void evaluate_get_task() {
		ResponseEntity respeusta = getAllTaskForAssignee("1");
		Assert.assertEquals(200, respeusta.getStatusCodeValue());
	}

	private ResponseEntity getAllTaskForAssignee(String assigneeId){
		String url = "http://localhost:8080/api//tasks/"+assigneeId;
		return template.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Task>>(){});
	}

	private ResponseEntity insertTask(HttpEntity entity, String url){
		return template.postForEntity(url, entity, Task.class);
	}

	@Test(expected = HttpServerErrorException.class)
	public void save() {

		ResponseEntity lista = getAllTaskForAssignee("1");
		assert lista.getBody()!=null;

		TaskRequest request = new TaskRequest();
		request.setAssigneeId(1L);
		request.setDue(new Date());
		request.setDescription("Aprender VUE");
		request.setCompletedAt(new Date());
		request.setCompleted(false);
		HttpEntity<TaskRequest> requestHttpEntity = new HttpEntity<>(request);
		String url = "http://localhost:8080/api/task";

		for(int i=((List<Task>) lista.getBody()).size(); i<=5; i++){
				insertTask(requestHttpEntity, url);
		}
	}
}
