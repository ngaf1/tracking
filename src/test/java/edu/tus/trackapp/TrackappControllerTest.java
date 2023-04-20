package edu.tus.trackapp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import edu.tus.trackapp.controllers.TrackappController;
import edu.tus.trackapp.dto.Application;
import edu.tus.trackapp.repositories.TrackappRepo;

//@RunWith(MockitoJUnitRunner.class)
class TrackappControllerTest {

	@InjectMocks
	private TrackappController trackappController;

	@Mock
	private TrackappRepo appRepo;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testGetApplicationForCategory() {
		Application application = new Application(1L, "Test App", "This is a test app", "Test Owner");
		List<Application> applicationList = Arrays.asList(application);

		when(appRepo.findAll()).thenReturn(applicationList);

		List<Application> result = trackappController.getApplicationForCategory();

		//assertEquals(applicationList, result);
		assertThat(applicationList).isEqualTo(result);
	}   

	@Test 
	void testGetApplicationById() { Application application = new
			Application(1L, "Test App", "This is a test app", "Test Owner");

	when(appRepo.findById(1L)).thenReturn(Optional.of(application));

	Optional<Application> result = trackappController.getApplication(1L);

	assertThat(Optional.of(application)).isEqualTo(result); }


	@Test 
	void testGetApplicationByName() { Application application = new
		Application(1L, "Test App", "This is a test app", "Test Owner");

	when(appRepo.findByName("Test App")).thenReturn(Optional.of(application));

	Optional<Application> result = trackappController.getApplication("Test App");

	assertThat(Optional.of(application)).isEqualTo(result);}

	 
	 @Test 
	 void testGetApplicationByOwner() { Application application = new
	 Application(1L, "Test App", "This is a test app", "Test Owner");
	  
	 when(appRepo.findByOwner("Test Owner")).thenReturn(Optional.of(application));
	  
	 Optional<Application> result =
	 trackappController.getAppbyOwner("Test Owner");
	  
	 assertThat(Optional.of(application)).isEqualTo(result);}
	  
	 @Test 
	 void testInsertApplication() { Application application = new
	  Application(1L, "Test App", "This is a test app", "Test Owner");
	  
	  when(appRepo.save(application)).thenReturn(application);
	  
	  ResponseEntity<Application> result =
	  trackappController.insertApplication(application);
	 
	 assertThat(application).isEqualTo(result.getBody()); 
	 assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK); }
	 
	 @Test 
	 void testId()
	 { Application app = new Application();
	 assertThat(app.getId()).isNull(); 
	 appRepo.save(app);
	 assertThat(app.getId()).isNull(); } 
	 
	 
	 @Test 
	 void testName() { Application app = new Application();
	  app.setName("Test Application"); 
	  assertThat(app.getName()).isEqualTo("Test Application"); }
	  
	 @Test 
	 void testDescription() { Application app = new Application();
	  app.setDescription("This is a test application");
	 assertThat(app.getDescription()).isEqualTo("This is a test application"); }
	 
	@Test 
	void testOwner() { Application app = new Application();
	 app.setOwner("John Doe"); 
	 assertThat(app.getOwner()).isEqualTo("John Doe"); }
	 
	 @Test 
	 void testToString() { 
		 Application app = new Application();
	 app.setId(1L); app.setName("Test Application"); app.setOwner("John Doe");
	 app.setDescription("This is a test application"); 
	 String expected = "Application{id=1, name='Test Application', owner=John Doe, description='This is a test application'}"; 
	 assertThat(expected).isEqualTo(app.toString()); }
	 

}

