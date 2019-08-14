package com.geosolution.app.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.geosolution.app.dao.StoreRepository;
import com.geosolution.app.entity.Store;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreServiceTest {
	
	@Autowired
	private StoreService storeService;
	@Mock
	private StoreRepository mockRepository;
	@Mock
	private WebTarget mockTarget;
	@Mock 
	private Client mockClient;
	
	private static List<Store> stores;
	
	private String JSON_DATA =
		     "{" 
		    		   + "  \"result\": [" 
		    		   + "    {" 
		    		   + "      \"id\": \"1\"," 
		    		   + "      \"name\": \"Julie Sherman\","                  
		    		   + "      \"gender\" : \"female\"," 
		    		   + "      \"latitude\" : \"37.33774833333334\"," 
		    		   + "      \"longitude\" : \"-121.88670166666667\""
		    		   + "    }," 
		    		   + "    {" 
		    		   + "      \"id\": \"2\"," 
		    		   + "      \"name\": \"Johnny Depp\","          
		    		   + "      \"gender\" : \"male\"," 
		    		   + "      \"latitude\" : \"37.336453\"," 
		    		   + "      \"longitude\" : \"-121.884985\""
		    		   + "    }" 
		    		   + "  ]" 
		    		   + "}"; 

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		stores = Arrays.asList(
					new Store("walmart", "GS", "Fish", "47.401738", "-122.19568"),
					new Store("kmart", "s", "Gashaw", "47.401738", "-122.19568")
				);
	}

	@Before
	public void setUp() throws Exception {
		storeService.setStoreRepository(mockRepository);
		storeService.setTarget(mockTarget);
		storeService.setClient(mockClient);
	}

	@Test
	public void testGetAllStores_PositiveTest() {
		when(mockRepository.findAll()).thenReturn(stores);
		List<Store> returnedStores = storeService.getAllStores();
		assertTrue(returnedStores.size() == 2);
	}
	
	@Test
	public void testReverseGeoCoding() {
		when(mockClient.target(Mockito.anyString()).queryParam(Mockito.anyString(), Mockito.anyObject())).thenReturn(mockTarget);
		when(mockTarget.request().get(String.class)).thenReturn(JSON_DATA);
		
		
		
	}

	@Test
	public void testPerformGeoCoding() {
//		fail("Not yet implemented");
	}

	@Test
	public void testGetLocation() {
//		fail("Not yet implemented");
	}

	@Test
	public void testAddNewStore() {
//		fail("Not yet implemented");
	}

	@Test
	public void testGetStoresByName() {
//		fail("Not yet implemented");
	}

}
