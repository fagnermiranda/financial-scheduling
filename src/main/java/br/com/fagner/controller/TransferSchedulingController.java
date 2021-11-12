package br.com.fagner.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fagner.dto.TransferSchedulingDTO;
import br.com.fagner.model.TransferScheduling;
import br.com.fagner.service.TransferSchedulingService;

@RestController
@RequestMapping("transfer-scheduling")
public class TransferSchedulingController {
	
	@Autowired
	private TransferSchedulingService transferSchedulingService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TransferSchedulingDTO createTransferScheduling(@RequestBody TransferSchedulingDTO dto) throws Exception {
		ModelMapper modelMapper = new ModelMapper();
		TransferScheduling transferScheduling = modelMapper.map(dto, TransferScheduling.class);
		return modelMapper.map(transferSchedulingService.createTransferScheduling(transferScheduling),TransferSchedulingDTO.class);
	}

}
