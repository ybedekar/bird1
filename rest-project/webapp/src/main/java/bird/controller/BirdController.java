package bird.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import bird.dto.BirdDto;
import bird.exceptionHandler.handler.BirdServiceException;
import bird.request.CreateBirdRequest;
import bird.response.BirdsResponse;
import bird.service.BirdService;

@RestController
@RequestMapping(value = "/birds")
public class BirdController {

	@Autowired
	private BirdService birdService;

	@RequestMapping(method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public ResponseEntity<BirdDto> createBird(@RequestBody CreateBirdRequest request,UriComponentsBuilder ucb) throws BirdServiceException{
		BirdDto birdDto = birdService.createBird(request);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucb.path("/birds/{id}").buildAndExpand(birdDto.getId()).toUri());

		return new ResponseEntity<BirdDto>(birdDto, headers, HttpStatus.CREATED);
	}


	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public ResponseEntity<BirdsResponse> getAllBirds() throws BirdServiceException{
		ResponseEntity<BirdsResponse> entity = null;
		BirdsResponse allBirds = birdService.getAllBirds();
		entity = new ResponseEntity<BirdsResponse>(allBirds,HttpStatus.OK);
		return entity;

	} 

	@RequestMapping(value = "{id}", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public ResponseEntity<BirdDto> getBird( @PathVariable("id") String id) throws BirdServiceException{
		ResponseEntity<BirdDto> entity = null;
		BirdDto birdDto = birdService.getBird(id);
		entity = new ResponseEntity<BirdDto>(birdDto,HttpStatus.OK);
		return entity;

	} 

	@RequestMapping(value = "{id}", method=RequestMethod.DELETE, produces="application/json")
	@ResponseBody
	public ResponseEntity<Void> deleteBird( @PathVariable("id") String id) throws BirdServiceException{
		ResponseEntity<Void> entity = null;
		birdService.deleteBird(id);
		entity = new ResponseEntity<Void>(HttpStatus.OK);
		return entity;

	} 


}
