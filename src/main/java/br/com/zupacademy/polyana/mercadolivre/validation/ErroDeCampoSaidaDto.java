package br.com.zupacademy.polyana.mercadolivre.validation;

public class ErroDeCampoSaidaDto {
        private String field;
        private String message;

        ErroDeCampoSaidaDto() { }

        public ErroDeCampoSaidaDto(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public String getMessage() {
            return message;
        }

}
