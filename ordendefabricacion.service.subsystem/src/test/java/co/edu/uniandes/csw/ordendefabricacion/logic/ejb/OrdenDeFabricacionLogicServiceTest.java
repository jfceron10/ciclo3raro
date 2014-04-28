
package co.edu.uniandes.csw.ordendefabricacion.logic.ejb;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.*;


import co.edu.uniandes.csw.ordendefabricacion.logic.dto.OrdenDeFabricacionDTO;
import co.edu.uniandes.csw.ordendefabricacion.logic.api.IOrdenDeFabricacionLogicService;
import co.edu.uniandes.csw.ordendefabricacion.persistence.OrdenDeFabricacionPersistence;
import co.edu.uniandes.csw.ordendefabricacion.persistence.api.IOrdenDeFabricacionPersistence;
import co.edu.uniandes.csw.ordendefabricacion.persistence.entity.OrdenDeFabricacionEntity;

@RunWith(Arquillian.class)
public class OrdenDeFabricacionLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(OrdenDeFabricacionLogicService.class.getPackage())
				.addPackage(OrdenDeFabricacionPersistence.class.getPackage())
				.addPackage(OrdenDeFabricacionEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IOrdenDeFabricacionLogicService ordenDeFabricacionLogicService;
	
	@Inject
	private IOrdenDeFabricacionPersistence ordenDeFabricacionPersistence;	

	@Before
	public void configTest() {
		try {
			clearData();
			insertData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clearData() {
		List<OrdenDeFabricacionDTO> dtos=ordenDeFabricacionPersistence.getOrdenDeFabricacions();
		for(OrdenDeFabricacionDTO dto:dtos){
			ordenDeFabricacionPersistence.deleteOrdenDeFabricacion(dto.getId());
		}
	}

	private List<OrdenDeFabricacionDTO> data=new ArrayList<OrdenDeFabricacionDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			OrdenDeFabricacionDTO pdto=new OrdenDeFabricacionDTO();
			pdto.setCantidad(generateRandom(Integer.class));
			pdto.setName(generateRandom(String.class));
			pdto.setEstado(generateRandom(String.class));
			pdto=ordenDeFabricacionPersistence.createOrdenDeFabricacion(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createOrdenDeFabricacionTest(){
		OrdenDeFabricacionDTO ldto=new OrdenDeFabricacionDTO();
		ldto.setCantidad(generateRandom(Integer.class));
		ldto.setName(generateRandom(String.class));
		ldto.setEstado(generateRandom(String.class));
		
		
		OrdenDeFabricacionDTO result=ordenDeFabricacionLogicService.createOrdenDeFabricacion(ldto);
		
		Assert.assertNotNull(result);
		
		OrdenDeFabricacionDTO pdto=ordenDeFabricacionPersistence.getOrdenDeFabricacion(result.getId());
		
		Assert.assertEquals(ldto.getCantidad(), pdto.getCantidad());	
		Assert.assertEquals(ldto.getName(), pdto.getName());	
		Assert.assertEquals(ldto.getEstado(), pdto.getEstado());	
	}
	
	@Test
	public void getOrdenDeFabricacionsTest(){
		List<OrdenDeFabricacionDTO> list=ordenDeFabricacionLogicService.getOrdenDeFabricacions();
		Assert.assertEquals(list.size(), data.size());
        for(OrdenDeFabricacionDTO ldto:list){
        	boolean found=false;
            for(OrdenDeFabricacionDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getOrdenDeFabricacionTest(){
		OrdenDeFabricacionDTO pdto=data.get(0);
		OrdenDeFabricacionDTO ldto=ordenDeFabricacionLogicService.getOrdenDeFabricacion(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getCantidad(), ldto.getCantidad());
		Assert.assertEquals(pdto.getName(), ldto.getName());
		Assert.assertEquals(pdto.getEstado(), ldto.getEstado());
        
	}
	
	@Test
	public void deleteOrdenDeFabricacionTest(){
		OrdenDeFabricacionDTO pdto=data.get(0);
		ordenDeFabricacionLogicService.deleteOrdenDeFabricacion(pdto.getId());
        OrdenDeFabricacionDTO deleted=ordenDeFabricacionPersistence.getOrdenDeFabricacion(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateOrdenDeFabricacionTest(){
		OrdenDeFabricacionDTO pdto=data.get(0);
		
		OrdenDeFabricacionDTO ldto=new OrdenDeFabricacionDTO();
		ldto.setId(pdto.getId());
		ldto.setCantidad(generateRandom(Integer.class));
		ldto.setName(generateRandom(String.class));
		ldto.setEstado(generateRandom(String.class));
		
		
		ordenDeFabricacionLogicService.updateOrdenDeFabricacion(ldto);
		
		
		OrdenDeFabricacionDTO resp=ordenDeFabricacionPersistence.getOrdenDeFabricacion(pdto.getId());
		
		Assert.assertEquals(ldto.getCantidad(), resp.getCantidad());	
		Assert.assertEquals(ldto.getName(), resp.getName());	
		Assert.assertEquals(ldto.getEstado(), resp.getEstado());	
	}
	
	public <T> T generateRandom(Class<T> objectClass){
		Random r=new Random();
		if(objectClass.isInstance(String.class)){
			String s="";
			for(int i=0;i<10;i++){
				char c=(char)(r.nextInt()/('Z'-'A')+'A');
				s=s+c;
			}
			return objectClass.cast(s);
		}else if(objectClass.isInstance(Integer.class)){
			Integer s=r.nextInt();
			return objectClass.cast(s);
		}else if(objectClass.isInstance(Long.class)){
			Long s=r.nextLong();
			return objectClass.cast(s);
		}else if(objectClass.isInstance(java.util.Date.class)){
			java.util.Calendar c=java.util.Calendar.getInstance();
			c.set(java.util.Calendar.MONTH, r.nextInt()/12);
			c.set(java.util.Calendar.DAY_OF_MONTH,r.nextInt()/30);
			c.setLenient(false);
			return objectClass.cast(c.getTime());
		} 
		return null;
	}
	
}