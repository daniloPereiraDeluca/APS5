package br.com.APS.service;

import java.io.Serializable;

import br.com.APS.data.ServerDTO;

public interface ServerService extends Serializable{

	public ServerDTO getServer();
}
