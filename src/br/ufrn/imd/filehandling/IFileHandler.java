package br.ufrn.imd.filehandling;

import java.util.ArrayList;

public interface IFileHandler {
	ArrayList<String> readData();
    void writeData(ArrayList<String> data);
}