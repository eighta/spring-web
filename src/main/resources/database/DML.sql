--APORTANTE: JAIME TORRES
INSERT INTO aportantes(
            --id, 
            estado, ciudad_municipio, departamento, tipo_accion, tipo_aportante, 
            adm_riesgos_laborales, adm_caja_compensacion, actividad_economica, 
            forma_pago, forma_presentacion, tipo_persona, naturaleza_juridica, 
            clase_aportante, tipo_identificacion, nro_identificacion, digito_verificacion, 
            nombre_razon_social, aporta_esap_minedu, ley_habeas_data, exonerado_pago_paraf_salud, 
            envio_soporte_pago_aportante, envio_soporte_pago_cotizantes, 
            correo_electronico, telefono, termino_actividades_comerciales, 
            concordato_reestructu_liquidac_cese, direccion_correspondencia, 
            fax, rep_legal_primer_nombre, rep_legal_segundo_nombre, rep_legal_primer_apellido, 
            rep_legal_segundo_apellido, rep_legal_tipo_identificacion, rep_legal_nro_identificacion, 
            rep_legal_digito_verificacion)
	    VALUES (--1,					--autogenerado 
	    		1,					--estado: 			ACTIVO 
	    		1,					--ciudad:		 	BOGOTA
	    		11,					--departamento:		BOGOTA	 	
	    		2,					--tipo accion: 		Reestructuracion
	    		1, 					--tipo aportante:	<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< ??? (TOMADO DE LA BASE DE DATOS)
	            '14-28',			--adm riesgos: 		SURA 
	            'CCF22',			--adm cajas: 		COLSUBISIDIO	 
	            '7230', 			--actividad econo:	<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< ??? (TOMADO DE LA BASE DE DATOS)
	            '?',				--forma pago:		<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< ???
	            '?',				--forma presenta: 	<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< ??? (ESTE CAMPO VA RELACIONADO CON LA SUCURSAL?)
	            'J',				--tipo persona: 	JURIDICA
	            2,					--naturaleza juri:	PRIVADA
	            'A',				--clase aportante: 	<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< ??? (TOMADO DE LA BASE DE DATOS) 
	            'NI',				--tipo identificac: NIT 
	            '860502327',		--nro identificacion 
	            8, 					--digito verifica:
	            'JAIME TORRES C Y CIA S.A.',	--razon social 
	            false,				--aporta esap-min: 
	            false,				--ley habeas data: 
	            false, 				--exondo paraf salud:
	            false,				--envio sopo pago apo: 
	            false, 				--envio sopo pago cot:
	            'contabilidad@jaimetorres.net',	--correo electronico
	            '4877853',			--telefono
	            null, 				--termino act comer
	            null,				--concordato
	            'Cr 26 No. 61c 07', --direccion
	            '2354107', 			--fax
	            'FELIPE', 			--rep legal 1er nombre
	            null, 				--rep legal 2do nombre
	            'JULIO', 			--rep legal 1er apelli
	            null, 				--rep legal 2do apell:
	            'CC', 				--rep legal tipo ide
	            '73123123', 		--rep legal nro ide
	            -1					--rep legal digito ver <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< ??? (SE DEBE CACULAR CON RESPECTO A NRO O SOLICITARLO?)
	            );
	            
--SUCURSAL: JAIME TORRES - SEDE PRINCIPAL
INSERT INTO sucursales_aportante(
            --id, 
            tipo_identificacion, nro_identificacion, codigo_sucursal,nombre_sucursal)
    	VALUES (
    			--1, 				--autogenerado 
    			'NI',				--tipo identificacion del aportante 
    			'860502327', 		--nro identificacion del aportante
    			'MAIN', 			--codigo de la sucursal
				'SEDE PRINCIPAL'	--nombre de la sucursal
				);
	            
--COTIZANTE
INSERT INTO cotizantes(
            adm_pension, nro_identificacion, tipo_identificacion, sucursal, tipo_cotizante, 
            subtipo_cotizante, extranjero_no_obligado_cotizar_pension, colombiano_residente_exterior, 
            departamento, ciudad_municipio, primer_nombre, segundo_nombre, 
            primer_apellido, segundo_apellido, adm_salud, adm_caja_compensacion, 
            salario_basico, salario_integral, tarifa_pension, tarifa_salud, 
            tarifa_caja_compensacion, tarifa_sena, tarifa_icbf, tarifa_esap, 
            tarifa_minedu, exonerado_pago_paraf_salud, estado, correo_electronico, 
            correo_electronico_cc, fecha_ingreso_laboral, tipo_soporte_pago, 
            adm_riesgos_laborales, centro_trabajo)
    VALUES ('PEN481',123456789, 'CC', 1, 5, 
            1,FALSE, FALSE, 
            1, 1, 'ADOLFITO', NULL, 
            'FICTY', NULL,'SALU14', 'CAJAS', 0, 
            FALSE, 0.5, 0.4, 0.5, 
            0.5, 0.5, 0.5, 0.5, 
            FALSE, 1, NULL, NULL, 
           '2016-01-01',0, 'RIES52', 
           NULL);	      
				
				
--NOVEDAD:Ingreso			
				
INSERT INTO novedades_ingreso(id, dia_ingreso) VALUES (1, 12);

INSERT INTO novedades(id, periodo_mes, periodo_anio, incapacidad_general, retiro, ingreso, 
            licencia_maternidad, variacion_permanente_salario, variacion_transitoria_salario, 
            vacaciones, traslado_eps, traslado_afp, aporte_voluntario, incapacidad_profesional, 
            variacion_centros_trabajo, suspension_temporal_contrato, cotizante_id)
    VALUES (1, 3, 2016, NULL, NULL, 1, 
            NULL, NULL, NULL, 
            NULL, NULL, NULL, NULL, NULL, 
            NULL, NULL, 1);
            
            
            
	            