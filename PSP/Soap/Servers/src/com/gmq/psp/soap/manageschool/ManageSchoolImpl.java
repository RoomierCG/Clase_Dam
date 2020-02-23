package com.gmq.psp.soap.manageschool;

import java.util.ArrayList;

public class ManageSchoolImpl implements ManageSchool {

	// private static HashMap<String, AsignatureRequest> listaAsignaturaAlumno = new
	// HashMap<String, AsignatureRequest>();
	ArrayList<AsignatureRequest> listaAsignatura = new ArrayList<AsignatureRequest>();
	ArrayList<StudentRequest> listaAlumnos = new ArrayList<StudentRequest>();
	ArrayList<EvaluationRequest> listaEvaluacion = new ArrayList<EvaluationRequest>();
	
	@Override
	public AsignatureResponse manageAsignature(AsignatureRequest objAsignature) {

		AsignatureRequest request = new AsignatureRequest();
		AsignatureResponse response = new AsignatureResponse();

		request.setIdAsignature(objAsignature.getIdAsignature());
		request.setDNI(objAsignature.getDNI());

		try {

			listaAsignatura.add(request);
			response.setStatus("Asignatura dada de alta correctamente");

			return response;
		} catch (Exception e) {

			response.setStatus("ERROR: Asignatura no dada de alta");
			return response;
		}
	}

	@Override
	public StudentResponse manageStudent(StudentRequest objStudent) {

		StudentRequest request = new StudentRequest();
		StudentResponse response = new StudentResponse();
		
		request.setName(objStudent.getName());
		request.setDNI(objStudent.getDNI());
		request.setAddress(objStudent.getAddress());
		
		try {
			
			listaAlumnos.add(request);
			
			response.setStatus("Alumno dada de alta correctamente");
			return response;
		} catch (Exception e) {

			response.setStatus("ERROR: Alumno no dada de alta");
			return response;
		}

	}

	@Override
	public EvaluationResponse manageEvalation(EvaluationRequest objEvaluation) {
		
		EvaluationResponse response = new EvaluationResponse();
		EvaluationRequest request = new EvaluationRequest();
		
		request.setStudent("");
		request.setAsiganature("");
		
		for (StudentRequest studentRequest : listaAlumnos) {
			if (studentRequest.getName().equals(objEvaluation.getStudent())) {
				request.setStudent(objEvaluation.getStudent());
			}
		}
		
		for (AsignatureRequest asignatureRequest : listaAsignatura) {
			if (asignatureRequest.getIdAsignature().equals(objEvaluation.getAsiganature())) {
				request.setAsiganature(objEvaluation.getAsiganature());
			}
		}
		
		request.setGrade(objEvaluation.getGrade());
		
		try {
			
			listaEvaluacion.add(request);
			
			if (request.getStudent().equals("") || request.getAsiganature().equals("")) {
				response.setStatus("ERROR: La nota no se ha podido dar de alta");
				return response;
			}
			
			response.setStatus("Nota correctamente dada de alta");
			return response;
		} catch (Exception e) {
			
			response.setStatus("ERROR: La nota no se ha podido dar de alta");
			return response;
		}
		
	}

}
