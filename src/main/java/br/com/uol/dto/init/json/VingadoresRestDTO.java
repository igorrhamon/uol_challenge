package br.com.uol.dto.init.json;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Slf4j
public class VingadoresRestDTO{
    private ArrayList<Vingadore> vingadores;
}


