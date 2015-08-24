/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ayrton monier
 */
package auxiliar;
import java.util.Date;
import java.text.SimpleDateFormat;
public class Data {

    //declarando variáveis do tipo String
    public static String mesExtenso, dataAtualExtenso, diaSemana, horaAtual, dataAtual, dataOntem, dataAtualJFormatted, dataAtualBD, horaAtualArquivo;
    public static String turno;
    
    public static int DIA, MES, ANO;
    public static int hora, minuto, segundo;

    static SimpleDateFormat horaFormatada;
    static Date horaData;

    //variáveis disponíveis para obter a hora
    public static void le_hora(){
        //hora atual
        horaFormatada = new SimpleDateFormat("HH:mm:ss");

        horaData = new Date();

        //hora formatada
        horaAtual = horaFormatada.format(horaData);

        horaAtualArquivo = horaData.getHours()+"."+horaData.getMinutes()+"."+horaData.getSeconds();

        //hora
        hora = horaData.getHours();
        //minuto
        minuto = horaData.getMinutes();
        //segundo
        segundo = horaData.getSeconds();
        
    }

    // variáveis disponíveis para obter datas por extenso ou inteira
    public static void le_data(){
         String dia, mes, ano;

        horaData = new Date();
        
        //vao servir para os campos que tem mascara
        dia = String.valueOf(horaData.getDate());
        mes = String.valueOf(horaData.getMonth());
        ano = String.valueOf(horaData.getYear()+1900);

        //retorna o dia da semana por extenso
        switch(horaData.getDay()){

            case 0: diaSemana = "Domingo";break;
            case 1: diaSemana = "Segunda";break;
            case 2: diaSemana = "Terça";break;
            case 3: diaSemana = "Quarta";break;
            case 4: diaSemana = "Quinta";break;
            case 5: diaSemana = "Sexta";break;
            case 6: diaSemana = "Sabado";break;
        }

         //retorna o mes por extenso
         switch(horaData.getMonth()){
            case 0: mesExtenso = "Janeiro";break;
            case 1: mesExtenso = "Fevereiro";break;
            case 2: mesExtenso = "março";break;
            case 3: mesExtenso = "Abril";break;
            case 4: mesExtenso = "Maio";break;
            case 5: mesExtenso = "Junho";break;
            case 6: mesExtenso = "Julho";break;
            case 7: mesExtenso = "Agosto";break;
            case 8: mesExtenso = "Setembro";break;
            case 9: mesExtenso = "Outubro";break;
            case 10:mesExtenso = "Novembro";break;
            case 11:mesExtenso = "Dezambro";break;

         }

        //dia do dia do mes formatado (mm)
        switch(horaData.getDate()){

            case 1: dia = "01";break;
            case 2: dia = "02";break;
            case 3: dia = "03";break;
            case 4: dia = "04";break;
            case 5: dia = "05";break;
            case 6: dia = "06";break;
            case 7: dia = "07";break;
            case 8: dia = "08";break;
            case 9: dia = "09";break;

        }

         //retorna o mes formatado (dd)
         switch(horaData.getMonth()){
            case 0: mes = "01";break;
            case 1: mes = "02";break;
            case 2: mes = "03";break;
            case 3: mes = "04";break;
            case 4: mes = "05";break;
            case 5: mes = "06";break;
            case 6: mes = "07";break;
            case 7: mes = "08";break;
            case 8: mes = "09";break;
            case 9: mes = "10";break;
            case 10:mes = "11";break;
            case 11:mes = "12";break;

         }

        //mÊs
        MES = horaData.getMonth()+1;// de 0 a 11
        //dia
        DIA = horaData.getDate();
        //ano
        ANO = (1900 + horaData.getYear());

        //data atual nao extenso
        dataAtual = DIA+"-"+MES+"-"+ANO;

        dataAtualBD = ANO+"-"+MES+"-"+DIA;


        //data atual formatada para os objetos JFORMATTEDTEXTFIELD
        dataAtualJFormatted = dia+mes+ano;

        //data atual por extenso
        dataAtualExtenso = diaSemana+", "+DIA+" de "+mesExtenso+" de "+ANO;

        //data de ontem
       // dataOntem = horaData.before(new java.util.Date());
    }//fim método le_data


        //validação de turno da tela de entrada
        public static void configuraTurno(int hora, int minuto){

        //se hora for de 6 a 14
        if(hora >= 6 && hora <= 14){
            
                if(hora == 14 && minuto <= 20){
                    turno = "Manha";
                }
                if(hora == 14 && minuto > 20){
                    turno = "Tarde";
                }
                else{
                    turno = "Manha";
                }
        }
       
        else if(hora >= 14 && hora <= 22){
                
                if(hora == 14 && minuto <= 20){
                    turno = "Manha";
                }

                if(hora == 22 && minuto <= 40){
                    turno = "Tarde";
                }
                
                if(hora == 22 && minuto > 40){
                    turno = "Noite";
                }

                else{
                    turno = "Tarde";
                }
        }

        else if(hora >= 22 || hora < 6){
                
                if(hora == 22 && minuto <= 40){
                    turno = "Tarde";
                }
                else{
                    turno = "Noite";
                }
        }

        }//fim método configuraTurno

        public static String mostraTurno(){return turno;}

        //PEGA A DATA DE ONTEM
        public static void dataOntem(){
            //pega data atual
            le_data();

        String dOntem;


                // 01 DE JANEIRO
                if(MES == 1 && DIA == 1)
                    dOntem = (ANO-1)+"-"+12+"-"+31;
                //01 DE FEVEREIRO
                else if(MES == 2 && DIA == 1)
                    dOntem = ANO+"-"+1+"-"+31;
                //01 DE MARÇO
                else if(MES == 3 && DIA == 1){
                int dia = 0;

                    if(ANO % 4 == 0)
                        dia = 29;
                    else if (ANO % 4 != 0)
                        dia = 28;

                    else if((ANO % 100 == 0) & (ANO % 400 != 0))
                        dia = 28;

                     dOntem = ANO+"-"+2+"-"+dia;
                }
               //01 DE ABRIL
                else if(MES == 4 && DIA == 1)
                    dOntem = ANO+"-"+3+"-"+31;
                //01 DE MAIO
                else if(MES == 5 && DIA == 1)
                    dOntem = ANO+"-"+4+"-"+30;
                //01 DE JUNHO
                else if(MES == 6 && DIA == 1)
                    dOntem = ANO+"-"+5+"-"+31;
                //01 DE JULHO
                else if(MES == 7 && DIA == 1)
                    dOntem = ANO+"-"+6+"-"+30;
                //01 DE AGOSTO
                else if(MES == 8 && DIA == 1)               
                    dOntem = ANO+"-"+7+"-"+31;
               //01 DE SETEMBRO
                else if(MES == 9 && DIA == 30)
                    dOntem = ANO+"-"+8+"-"+31;
               //01 DE OUTUBRO
                else if(MES == 10 && DIA == 1)
                    dOntem = ANO+"-"+9+"-"+30;
               //01 DE NOVEMBRO
                else if(MES == 11 && DIA == 1)
                    dOntem = ANO+"-"+10+"-"+31;
               //01 DE DEZEMBRO
                else if(MES == 12 && DIA == 1)
                    dOntem = ANO+"-"+11+"-"+30;
               //QUALQUER DATA A NÃO SER AS ANTERIORES DOS IF
                else
                    dOntem = ANO+"-"+MES+"-"+(DIA - 1);

                //data de Ontem
                dataOntem = dOntem;

        } //FIM DATA DE ONTEM



}