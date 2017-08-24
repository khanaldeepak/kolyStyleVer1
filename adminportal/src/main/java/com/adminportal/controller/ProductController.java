package com.adminportal.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.adminportal.domain.Product;
import com.adminportal.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addProduct(Model model){
		Product product = new Product();
		model.addAttribute("product", product);
		return "addProduct";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addProductPost(
			@ModelAttribute("product") Product product, HttpServletRequest request
			){
			productService.save(product);

		 //Get the uploaded files and store them
        List<MultipartFile> files = product.getProductImage();
        if (null != files && files.size() > 0) 
        {
            for (MultipartFile multipartFile : files) {
            	
            	try {
					byte[] bytes = multipartFile.getBytes();
					
					//To generate random number 50 is max and 10 is min
					Random rand = new Random();
					int  newrandom = rand.nextInt(50) + 10;
					
					/*Using Product Id with Time Stamp and Random Number for File name so we can 
					  have unique file always within product id folder*/
					Timestamp timestamp = new Timestamp(System.currentTimeMillis());
					String newFileName = product.getId()+timestamp.getTime()+newrandom+".png";
					
					String PATH = "src/main/resources/static/image/product/";
				    
					String folderName =  PATH.concat(Long.toString(product.getId()));
					
					//Create Folder with product ID as name
					File directory = new File(folderName);
				    if (! directory.exists()){
				        directory.mkdir();     
				    }
					 
					 BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(folderName+"/"+newFileName)));
					 stream.write(bytes);
					 stream.close();
            	} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        }
		
		
/*		MultipartFile productImage = product.getProductImage();
		
		try{
			byte[] bytes = productImage.getBytes();
		
			//Name to assign to image
			String name = product.getId()+".png";
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/product/"+name)));
		    stream.write(bytes);
		    stream.close();
		}catch (Exception e){
			e.printStackTrace();
		}*/
		
		
		
		return "redirect:productList";
	}
	

	@RequestMapping("/productInfo")
	public String productInfo(@RequestParam("id") Long id, Model model){
		Product product = productService.findOne(id);
		
		//Get Product Image name from the Product Folder
		List<String> productImageList = new ArrayList<String>();
		String PATH = "src/main/resources/static/image/product/";
	    
		String folderName =  PATH.concat(Long.toString(product.getId()));
		
		File folder = new File(folderName);
		File[] listOfFiles = folder.listFiles();
		int counting = listOfFiles.length;
		for(int i=0;i<counting;i++){
			String productImageName = listOfFiles[i].getName();
			productImageList.add(productImageName);
		}
		
		model.addAttribute("productImageList", productImageList);
		model.addAttribute("product", product);
		
		return "productInfo";
		
	}
	
	@RequestMapping("/updateProduct")
	public String updateProduct(@RequestParam("id") Long id, Model model){
		Product product = productService.findOne(id);
		model.addAttribute("product", product);
		return "updateProduct";
	}
	
/*	@RequestMapping(value="/updateProduct", method=RequestMethod.POST)
	public String updateProductPost(
			@ModelAttribute("product") Product product, HttpServletRequest request
			){
		productService.save(product);
		
		MultipartFile productImage = product.getProductImage();
		
		if(!productImage.isEmpty()){
			try{
				byte[] bytes = productImage.getBytes();
			
				//Name to assign to image
				String name = product.getId()+".png";
				Files.delete(Paths.get("src/main/resources/static/image/product/"+name));
				
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/product/"+name)));
			    stream.write(bytes);
			    stream.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		
		
		
		return "redirect:/product/productInfo?id="+product.getId();
	}*/
	
	
	@RequestMapping("/productList")
	public String productList(Model model){
		List<Product> productList = productService.findAll();
		model.addAttribute("productList", productList);
		
		return "productList";
		
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(
			@ModelAttribute("id") String id, Model model
			){
		productService.removeOne(Long.parseLong(id.substring(11)));
		List<Product> productList = productService.findAll();
		model.addAttribute("productList", productList);
		
		return "redirect:/product/productList";
		
	}
	
	
}
