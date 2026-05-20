package co.edu.uco.ucoparking.features.student.registernewstudent.application.inputport.mapper.student;

import co.edu.uco.ucoparking.application.inputport.mapper.DTOMapper;
import co.edu.uco.ucoparking.features.student.registernewstudent.application.inputport.dto.RegisterNewStudentDTO;
import co.edu.uco.ucoparking.features.student.registernewstudent.application.usecase.domain.RegisterNewStudentDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterNewStudentMapper extends DTOMapper<RegisterNewStudentDTO, RegisterNewStudentDomain> {
    //RegisterNewStudentMapper INSTANCE = Mappers.getMapper(RegisterNewStudentMapper.class);

    // TO <-> DTO
    //RegisterNewStudentDTO toDTO(RegisterNewStudentInputTO to);
    //RegisterNewStudentInputTO toTO(RegisterNewStudentDTO dto);

    // TO <-> Domain
    //RegisterNewStudentDomain toDomain(RegisterNewStudentInputTO to);
    //RegisterNewStudentInputTO toTO(RegisterNewStudentDomain domain);

    // Domain -> Entity (solo los campos básicos, relaciones deben resolverse aparte)
    //@Mapping(target = "academicProgram", ignore = true)
    //@Mapping(target = "idType", ignore = true)
    //@Mapping(target = "name", ignore = true)
    //@Mapping(target = "firstLastName", ignore = true)
    //@Mapping(target = "secondLastName", ignore = true)
    //@Mapping(target = "phoneNumber", source = "mobileNumber")
    //StudentEntity domainToEntity(RegisterNewStudentDomain domain);
}
