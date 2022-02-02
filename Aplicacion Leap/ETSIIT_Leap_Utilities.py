# -*- coding: utf-8 -*-

import sys, thread, time
import Leap
from Leap import CircleGesture, KeyTapGesture, ScreenTapGesture, SwipeGesture
from Tkinter import *
from Tkinter import Frame
import tkFont
from PIL import ImageTk,Image

###########################################INTERFAZ GRAFICA#####################################################
ventanaPrincipal = Tk()
ventanaPrincipal.title("NPI Utilities")
ventanaPrincipal.geometry("1800x1500")

#################INTERFAZ PRINCIPAL################################
framePrincipal = Frame(ventanaPrincipal)

#Cargamos las imagenes
renderComedor = ImageTk.PhotoImage(Image.open('images/Comedor.jpg'))
renderActividades = ImageTk.PhotoImage(Image.open('images/Actividades.png'))
renderParking = ImageTk.PhotoImage(Image.open('images/Parking.jpg'))

#Creamos el titulo
tituloPrincipal = Label(framePrincipal, text="ETSIIT Utilities")
tituloPrincipal.config(padx=5, pady=5, anchor=CENTER, fg="#d16200", font=("Verdana", 50))
tituloPrincipal.grid(columnspan=4) #Centramos el titulo

textoInformativo = Label(framePrincipal, text="Consulta los platos del comedor, revisa las actividades "
                                             "de la semana o comprueba la disponibilidad de aparcamiento")
textoInformativo.config(padx=5, pady=5, anchor=CENTER, fg="#d16200", font=("Verdana", 20))
textoInformativo.grid(columnspan=4) #Centramos el texto

#Anhadimos las imagenes al frame
imgComedor = Label(framePrincipal, image=renderComedor, text="Comedor", fg="#d16200", font=("Verdana", 20), compound=TOP)
imgActividades = Label(framePrincipal, image=renderActividades, text="Actividades", fg="#d16200", font=("Verdana", 20), compound=TOP)
imgParking = Label(framePrincipal, image=renderParking, text="Parking", fg="#d16200", font=("Verdana", 20), compound=TOP)

#Colocamos los elementos en el frame
imgComedor.grid(row=2, column=0, padx=5, pady=10)
imgActividades.grid(row=2, column=1, padx=30, pady=10)
imgParking.config(padx=5, pady=5, anchor=CENTER)
imgParking.grid(columnspan=4)

framePrincipal.pack()

#################INTERFAZ PARKING################################
frameParking = Frame(ventanaPrincipal)

#Cargamos las imagenes
renderBicis = ImageTk.PhotoImage(Image.open('images/pbicis.jpg'))
renderPatinetes = ImageTk.PhotoImage(Image.open('images/ppatinetes.jpg'))
renderCoches = ImageTk.PhotoImage(Image.open('images/pcoches.jpg'))
renderMotos = ImageTk.PhotoImage(Image.open('images/pmotos.jpg'))

#Creamos el titulo
tituloParking = Label(frameParking,text="Información sobre el parking")
tituloParking.config(padx=5, pady=20,anchor = CENTER, fg="#d16200",font=("Verdana",24))

#Anhadimos las imagenes al frame
imgBicis = Label(frameParking,image = renderBicis, text="Parking de bicis",font=("Verdana",18), compound=TOP)
imgPatinetes = Label(frameParking,image = renderPatinetes, text="Parking de patinetes",font=("Verdana",18), compound=TOP)
imgCoches = Label(frameParking,image = renderCoches, text="Parking de Coches",font=("Verdana",18), compound=TOP)
imgMotos = Label(frameParking,image = renderMotos, text="Parking de motos",font=("Verdana",18), compound=TOP)
textoBicis = Label(frameParking,text="0 plazas libres", fg="red",font=("Verdana",20))
textoPatinetes = Label(frameParking,text="9 plazas libres", fg="green",font=("Verdana",20))
textoCoches = Label(frameParking,text="2 plazas libres", fg="green",font=("Verdana",20))
textoMotos = Label(frameParking,text="15 plazas libres", fg="green",font=("Verdana",20))

#Colocamos los elementos en el frame
tituloParking.grid(columnspan = 4) #Centramos el titulo
imgBicis.grid(padx=10, pady=10,row=1,column=0)
textoBicis.grid(padx=(5,70), row=1, column=1)
imgPatinetes.grid(padx=10, pady=10,row=1,column=2)
textoPatinetes.grid(padx=(5,70), row=1, column=3)
imgCoches.grid(padx=10, pady=10,row=2,column=0)
textoCoches.grid(padx=(5,70), row=2, column=1)
imgMotos.grid(padx=10, pady=10,row=2,column=2)
textoMotos.grid(padx=(5,70), row=2, column=3)

#################INTERFAZ ACTIVIDADES################################
#La siguiente funcion es necesaria para ajustar los textos en varias lineas
def on_label_resize(event):
    event.widget["wraplength"] = event.width

frameActividades = Frame(ventanaPrincipal)

canvas = Canvas(frameActividades)

scrollbar = Scrollbar(frameActividades, orient="vertical", command=canvas.yview)
scrollbar.pack(side="right", fill="y")
frameCanvas = Frame(canvas)
frameCanvas.bind(
        "<Configure>",
        lambda e: canvas.configure(
            scrollregion=canvas.bbox("all")
        )
    )
canvas.create_window((0, 0), window=frameCanvas, anchor="nw")
canvas.configure(yscrollcommand=scrollbar.set)

#Creamos los textos a mostrar
fontStyle = tkFont.Font(family="Verdana", size=22)
text1 = StringVar(value=("Charla/taller: \"Desarrollo orientado "
                         "a comportamiento como mecanismo de automatización de pruebas\". "
                         "Imparte Nicolás Afonso Alonso, Arquitecto de Productos Emergentes "
                         "y Experto Edison en Desarrollo de Software en Schneider Electric S.A. Organiza "
                         "el Máster en Desarrollo de Software del Departamento de Lenguajes y Sistemas Informáticos.\n"
                         "Lugar y Fecha: Lunes 8, 16:30-18:30, aula 1.7 y online."))
text2 = StringVar(value=("Talleres de robótica y "
                         "tecnologías educativas.  Organizado por la Asociación Andaluza de Graduados e "
                         "Ingenieros Técnicos de Telecomunicación (AAGIT).\n"
                         "Lugar y Fecha: 8, 9, 15 y 16 de noviembre de 1:001 a 15:00 en aula -1.1."))
text3 = StringVar(value=("Taller: \"Pandoc\". Organizado por la OSL.\n"
                         "Lugar y Fecha: 10 de noviembre, 19.30, aula 0.6 y online."))
text4 = StringVar(value=("Taller: \"Gestión de versiones con Pyenv\".  "
                         "Imparte Christian Prada, Desarrollador Full Stack Backend con más de 16 años de "
                         "experiencia en Linux/Unix y Ambassador de Fedora y Technical Lead de la compañía "
                         "Zurich. Organizado por Yes We Tech, Python Granada y OSL.\n"
                         "Lugar y Fecha: Martes 9 de noviembre, 19:30, aula 0.7."))
text5 = StringVar(value=("Charla sobre experiencias profesionales. "
                         "Impartido por Francisco Benítez (FIDESOL). Organizado por la asignatura de Planificación"
                         "y Gestión de Proyectos Informáticos del Máster Universitario de Ingeniería Informática.\n"
                         "Lugar y Fecha: Jueves 11 de noviembre, 17:00, aula 1.6."))

text6 = StringVar(value=("Charla/taller: \"Aterriza tu idea\". Si quieres presentarte al V "
                         "concurso de Ideas, y no sabes cómo presentar la documentación, o simplemente quieres "
                         "saber cómo presentar una idea de forma convincente, ¡asiste a este taller para "
                         "aumentar tus probabilidades de éxito!\n"
                         "Fecha: 9 y 16 de noviembre."))
text7 = StringVar(value=("Talleres de Google Cloud Platform. Organizado por AI Lab Granada. "
                         "introducción y laboratorios interactivos sobre herramientas de \"Google Cloud Platform\". "
                         "Necesaria inscripción (solo 100 primeras solicitudes, prioritarios últimos cursos)."
                         "Contenidos:\n\n\tBigQuery for Data Analysis I "
                         "\n\t\tLABBigQuery: Qwik Start - Línea de comandos 30 minutos"
                         "\n\t\tIntroducción a SQL para BigQuery y Cloud SQL>  1 hora"
                         "\n\t\tDatos metereológicos en BigQuery>  35 minutos"
                         "\n\t\tData Studio: Qwik Start>  30 minutos"
                         "\n\n\tIntro to BigQuery: Analytics & Machine Learning"
                         "\n\t\tCómo transferir nuevos conjuntos de datos a BigQuery>  1 hora"
                         "\n\t\tCómo comenzar a usar BQML>  45 minutos"
                         "\n\t\tPrediga las tarifas de taxis con un modelo de previsión de AA de BigQuery>  1 hora\n"
                         "Lugar y Fecha: 12 y 19 de noviembre, aula B1."))
text8 = StringVar(value=("Charla GDG: \"DRM en videojuegos: técnicas de malware "
                         "para luchar contra la piratería\". impartido por Benito Palacios. Organiza GDG Granada.\n"
                         "Lugar y Fecha: 16 de noviembre, 19:30,Salón de Grados."))
text9 = StringVar(value=("Charla: Cómo evitar estamparse tras acabar los estudios. "
                         "Imparte Israel Blancas (Red Hat Quality Engineer).\n"
                         "Lugar y Fecha: 23 de noviembre, 19:30, Salón de Grados."))

text10 = StringVar(value=("Abierto el plazo de inscripción en el programa de Capacitación Profesional de la ETSIIT. Si ya "
                          "estás pensando en tu carrera profesional, sácale partido en este programa. Los/as participantes "
                          "tendrán que asistir con aprovechamiento a al menos 12 activididades incluidas en el programa "
                          "(talleres, charlas sobre cómo elabora el CV, charlas con empresas, etc). Superadas esas actividades, "
                          "se obtendrá un diploma con el listado de actividades, que podrás adjuntar al CV. Para más información "
                          "e inscripción, escribir a etsiit-extension@ugr.es con el asunto: \"Capacitación profesional 21/22\""))

text11 = StringVar(value=("Inscripción al V concurso de Ideas. ¿Tienes en mente una idea novedosa "
                          "y crees que podrías llevarla a la práctica? Si eres estudiante, PAS, PDI o alumni, participa y gana "
                          "hasta 1500€, y soporte para llevarla a cabo.\n"
                          "Fecha: Hasta el 17 de noviembre de 2021"))
text12 = StringVar(value=("Inscripción a los Premios Ingenio Junior 2021. Si has defendido tu TFG "
                          "este año, presenta tu candaidatura. Organizado por el COITTA-AAGIT.\n"
                          "Fecha: Hasta el 30 de noviembre de 2021"))

#Cargamos las imagenes
renderAct1 = ImageTk.PhotoImage(Image.open('images/Actividad1.jpg'))
renderAct2 = ImageTk.PhotoImage(Image.open('images/Actividad2.jpeg'))
renderAct3 = ImageTk.PhotoImage(Image.open('images/Actividad3.jpeg'))
renderAct4 = ImageTk.PhotoImage(Image.open('images/Actividad4.png'))
renderAct5 = ImageTk.PhotoImage(Image.open('images/Actividad5.jpg'))
renderAct6 = ImageTk.PhotoImage(Image.open('images/Actividad6.jpg'))
renderAct7 = ImageTk.PhotoImage(Image.open('images/Actividad7.png'))
renderAct8 = ImageTk.PhotoImage(Image.open('images/Actividad8.jpeg'))
renderAct9 = ImageTk.PhotoImage(Image.open('images/Actividad5.jpg'))
renderAct10 = ImageTk.PhotoImage(Image.open('images/Actividad5.jpg'))
renderAct11 = ImageTk.PhotoImage(Image.open('images/Actividad6.jpg'))
renderAct12 = ImageTk.PhotoImage(Image.open('images/Actividad12.jpg'))

#Creamos el titulo
tituloActividades = Label(frameCanvas, text="Actividades de la semana")
tituloActividades.config(padx=5, pady=5, anchor=CENTER, fg="#d16200", font=("Verdana", 24))
tituloActividades.grid(columnspan=4) #Centramos el titulo

scrollbar.config(command=canvas.yview)
canvas.pack(expand=True, fill='both')

#Anhadimos las imagenes y los textos del apartado Actividades
labelActividades = Label(frameCanvas, text="Actividades", fg="#d16200", font=("Verdana", 40))
labelActividades.config(padx=10, pady=0, anchor=CENTER)
labelActividades.grid(row=1, columnspan=4)
imgAct1 = Label(frameCanvas, image=renderAct1)
imgAct1.grid(row=2, column=0, padx=20, pady=20, sticky="e")
label1 = Label(frameCanvas, textvariable=text1, font=fontStyle, width=75)
label1.grid(row=2, column=1, pady=20, sticky="nsw")
label1.bind("<Configure>", on_label_resize)
imgAct2 = Label(frameCanvas, image=renderAct2)
imgAct2.grid(row=3, column=0, padx=20, pady=20, sticky="e")
label2 = Label(frameCanvas, textvariable=text2, font=fontStyle, width=75)
label2.grid(row=3, column=1, pady=20, sticky="nsw")
label2.bind("<Configure>", on_label_resize)
imgAct3 = Label(frameCanvas, image=renderAct3)
imgAct3.grid(row=4, column=0, padx=20, pady=20, sticky="e")
label3 = Label(frameCanvas, textvariable=text3, font=fontStyle, width=75)
label3.grid(row=4, column=1, pady=20, sticky="nsw")
label3.bind("<Configure>", on_label_resize)
imgAct4 = Label(frameCanvas, image=renderAct4)
imgAct4.grid(row=5, column=0, padx=20, pady=20, sticky="e")
label4 = Label(frameCanvas, textvariable=text4, font=fontStyle, width=75)
label4.grid(row=5, column=1, pady=20, sticky="nsw")
label4.bind("<Configure>", on_label_resize)
imgAct5 = Label(frameCanvas, image=renderAct5)
imgAct5.grid(row=6, column=0, padx=20, pady=20, sticky="e")
label5 = Label(frameCanvas, textvariable=text5, font=fontStyle, width=75)
label5.grid(row=6, column=1, pady=20, sticky="nsw")
label5.bind("<Configure>", on_label_resize)

#Anhadimos las imagenes y los textos del apartado Eventos
labelEventos = Label(frameCanvas, text="Eventos", fg="#d16200", font=("Verdana", 40))
labelEventos.config(padx=10, pady=0, anchor=CENTER)
labelEventos.grid(row=7, columnspan=4)
imgAct6 = Label(frameCanvas, image=renderAct6)
imgAct6.grid(row=8, column=0, padx=20, pady=20, sticky="e")
label6 = Label(frameCanvas, textvariable=text6, font=fontStyle, width=75)
label6.grid(row=8, column=1, pady=20, sticky="nsw")
label6.bind("<Configure>", on_label_resize)
imgAct7 = Label(frameCanvas, image=renderAct7)
imgAct7.grid(row=9, column=0, padx=20, pady=20, sticky="e")
label7 = Label(frameCanvas, textvariable=text7, font=fontStyle, width=75)
label7.grid(row=9, column=1, padx=0, pady=20, sticky="nws")
label7.bind("<Configure>", on_label_resize)
imgAct8 = Label(frameCanvas, image=renderAct8)
imgAct8.grid(row=10, column=0, padx=20, pady=20, sticky="e")
label8 = Label(frameCanvas, textvariable=text8, font=fontStyle, width=75)
label8.grid(row=10, column=1, pady=20, sticky="nsw")
label8.bind("<Configure>", on_label_resize)
imgAct9 = Label(frameCanvas, image=renderAct9)
imgAct9.grid(row=11, column=0, padx=20, pady=20, sticky="e")
label9 = Label(frameCanvas, textvariable=text9, font=fontStyle, width=75)
label9.grid(row=11, column=1, pady=20, sticky="nsw")
label9.bind("<Configure>", on_label_resize)

#Anhadimos las imagenes y los textos del apartado Convocatoria
labelConvocatorias = Label(frameCanvas, text="Convocatorias", fg="#d16200", font=("Verdana", 40))
labelConvocatorias.config(padx=10, pady=0, anchor=CENTER)
labelConvocatorias.grid(row=12, columnspan=4)
imgAct10 = Label(frameCanvas, image=renderAct10)
imgAct10.grid(row=13, column=0, pady=20, sticky="e")
label10 = Label(frameCanvas, textvariable=text10, font=fontStyle, width=75)
label10.grid(row=13, column=1, pady=20, sticky="nsw")
label10.bind("<Configure>", on_label_resize)

#Anhadimos las imagenes y los textos del apartado Premios
labelPremios = Label(frameCanvas, text="Premios", fg="#d16200", font=("Verdana", 40))
labelPremios.config(padx=10, pady=0, anchor=CENTER)
labelPremios.grid(row=14, columnspan=4)
imgAct11 = Label(frameCanvas, image=renderAct11)
imgAct11.grid(row=15, column=0, pady=20, sticky="e")
label11 = Label(frameCanvas, textvariable=text11, font=fontStyle, width=75)
label11.grid(row=15, column=1, pady=20, sticky="nsw")
label11.bind("<Configure>", on_label_resize)
imgAct12 = Label(frameCanvas, image=renderAct12)
imgAct12.grid(row=16, column=0, pady=20, sticky="e")
label12 = Label(frameCanvas, textvariable=text12, font=fontStyle, width=75)
label12.grid(row=16, column=1, pady=20, sticky="nsw")
label12.bind("<Configure>", on_label_resize)

#################INTERFAZ COMEDOR##################
frameComedor = Frame(ventanaPrincipal)
#Cargamos las imagenes
renderPrimeros = ImageTk.PhotoImage(Image.open('images/primeros.jpg')) 
renderSegundos = ImageTk.PhotoImage(Image.open('images/segundos.jpg')) 
renderPostres = ImageTk.PhotoImage(Image.open('images/postres.jpg')) 
renderMenuDiario = ImageTk.PhotoImage(Image.open('images/menudiario.jpg')) 

#Creamos el titulo
tituloComedor = Label(frameComedor,text="Información sobre el comedor")
tituloComedor.config(padx=5, pady=5,anchor = CENTER, fg="#d16200",font=("Verdana",24))

#Anhadimos las imagenes al frame
imgPrimeros = Label(frameComedor,image = renderPrimeros)
imgSegundos = Label(frameComedor,image = renderSegundos)
imgPostres = Label(frameComedor,image = renderPostres)
imgMenuDiario = Label(frameComedor,image = renderMenuDiario)

#Colocamos los elementos en el frame
tituloComedor.grid(columnspan = 4) #Centramos el titulo
imgPrimeros.grid(padx=10, pady=20,row=1,column=0)
imgSegundos.grid(padx=10, pady=20,row=1,column=1)
imgPostres.grid(padx=10, pady=10,row=2,column=0)
imgMenuDiario.grid(padx=10, pady=10,row=2,column=1)

#################INTERFAZ PRIMEROS PLATOS##################
framePrimeros = Frame(ventanaPrincipal)
#Cargamos las imagenes
renderCrema = ImageTk.PhotoImage(Image.open('images/cremacalabaza.jpg'))
renderEnsalada = ImageTk.PhotoImage(Image.open('images/ensalada.jpg'))
renderEnsaladilla = ImageTk.PhotoImage(Image.open('images/ensaladilla.jpg'))
renderGarbanzos = ImageTk.PhotoImage(Image.open('images/garbanzosconbacalao.jpg'))
renderLentejas = ImageTk.PhotoImage(Image.open('images/lentejas.jpg'))
renderMacarrones = ImageTk.PhotoImage(Image.open('images/macarrones.jpeg'))
renderSopa = ImageTk.PhotoImage(Image.open('images/sopafideos.jpg'))
renderTortilla = ImageTk.PhotoImage(Image.open('images/tortilla.jpg'))
renderPizza = ImageTk.PhotoImage(Image.open('images/pizza.jpg'))

#Creamos el titulo
tituloPrimeros = Label(framePrimeros,text="Oferta de primeros platos")
tituloPrimeros.config(padx=5,pady=5,anchor = CENTER,fg="#d16200",font=("Verdana",24))

#Anhadimos las imagenes al frame
imgCrema = Label(framePrimeros,text="Crema de Calabaza",font=("Verdana",12),image = renderCrema,compound=TOP) #El atributo "compound" permite indicar como se colocan el texto y la imagen (entre si)
imgEnsalada = Label(framePrimeros,text="Ensalada",font=("Verdana",12),image = renderEnsalada,compound=TOP)
imgEnsaladilla = Label(framePrimeros,text="Ensaladilla",font=("Verdana",12),image = renderEnsaladilla,compound=TOP)
imgGarbanzos = Label(framePrimeros,text="Garbanzos con Bacalado",font=("Verdana",12),image = renderGarbanzos,compound=TOP)
imgLentejas = Label(framePrimeros,text="Lentejas",font=("Verdana",12),image = renderLentejas,compound=TOP)
imgMacarrones = Label(framePrimeros,text="Macarrones",font=("Verdana",12),image = renderMacarrones,compound=TOP)
imgSopa = Label(framePrimeros,text="Sopa de fideos",font=("Verdana",12),image = renderSopa,compound=TOP)
imgTortilla = Label(framePrimeros,text="Tortilla",font=("Verdana",12),image = renderTortilla,compound=TOP)
imgPizza = Label(framePrimeros,text="Pizza",font=("Verdana",12),image = renderPizza,compound=TOP)

#Colocamos los elementos en el frame
tituloPrimeros.grid(columnspan = 4) #Centramos el titulo
imgCrema.grid(padx=5, pady=5,row=1,column=0)
imgEnsalada.grid(padx=5, pady=5,row=1,column=1)
imgEnsaladilla.grid(padx=5, pady=5,row=1,column=2)
imgGarbanzos.grid(padx=5, pady=5,row=2,column=0)
imgLentejas.grid(padx=5, pady=5,row=2,column=1)
imgMacarrones.grid(padx=5, pady=5,row=2,column=2)
imgSopa.grid(padx=5, pady=5,row=3,column=0)
imgTortilla.grid(padx=5, pady=5,row=3,column=1)
imgPizza.grid(padx=5, pady=5,row=3,column=2)

#################INTERFAZ SEGUNDOS PLATOS##################
frameSegundos= Frame(ventanaPrincipal)
#Cargamos las imagenes
renderArroz = ImageTk.PhotoImage(Image.open('images/arroz.jpg'))
renderAlitas = ImageTk.PhotoImage(Image.open('images/alitas.jpg'))
renderCalamares = ImageTk.PhotoImage(Image.open('images/calamares.jpg'))
renderCanelones = ImageTk.PhotoImage(Image.open('images/canelones.jpg'))
renderCarneSalsa = ImageTk.PhotoImage(Image.open('images/carnesalsa.jpg'))
renderCarneGuisada = ImageTk.PhotoImage(Image.open('images/carneguisada.jpg'))
renderMerluza = ImageTk.PhotoImage(Image.open('images/merluza.jpg'))
renderPimientos = ImageTk.PhotoImage(Image.open('images/pimientosrellenos.jpg'))
renderPlato = ImageTk.PhotoImage(Image.open('images/platoalpujarreno.jpg'))

#Creamos el titulo
tituloSegundos = Label(frameSegundos,text="Oferta de segundos platos")
tituloSegundos.config(padx=5,pady=5,anchor = CENTER,fg="#d16200",font=("Verdana",24))

#Anhadimos las imagenes al frame
imgArroz = Label(frameSegundos,text="Arroz al horno",font=("Verdana",12),image = renderArroz,compound=TOP) #El atributo "compound" permite indicar como se colocan el texto y la imagen (entre si)
imgAlitas = Label(frameSegundos,text="Alitas de pollo",font=("Verdana",12),image = renderAlitas,compound=TOP)
imgCalamares = Label(frameSegundos,text="Calamares",font=("Verdana",12),image = renderCalamares,compound=TOP)
imgCanelones = Label(frameSegundos,text="Canelones de espinacas",font=("Verdana",12),image = renderCanelones,compound=TOP)
imgCarneSalsa = Label(frameSegundos,text="Carne en salsa",font=("Verdana",12),image = renderCarneSalsa,compound=TOP)
imgCarneGuisada = Label(frameSegundos,text="Carne guisada",font=("Verdana",12),image = renderCarneGuisada,compound=TOP)
imgMerluza = Label(frameSegundos,text="Merluza a la romana",font=("Verdana",12),image = renderMerluza,compound=TOP)
imgPimientos = Label(frameSegundos,text="Pimientos rellenos",font=("Verdana",12),image = renderPimientos,compound=TOP)
imgPlato = Label(frameSegundos,text="Plato Alpujarreño",font=("Verdana",12),image = renderPlato,compound=TOP)

#Colocamos los elementos en el frame
tituloSegundos.grid(columnspan = 4) #Centramos el titulo
imgArroz.grid(padx=5, pady=5,row=1,column=0)
imgAlitas.grid(padx=5, pady=5,row=1,column=1)
imgCalamares.grid(padx=5, pady=5,row=1,column=2)
imgCanelones.grid(padx=5, pady=5,row=2,column=0)
imgCarneSalsa.grid(padx=5, pady=5,row=2,column=1)
imgCarneGuisada.grid(padx=5, pady=5,row=2,column=2)
imgMerluza.grid(padx=5, pady=5,row=3,column=0)
imgPimientos.grid(padx=5, pady=5,row=3,column=1)
imgPlato.grid(padx=5, pady=5,row=3,column=2)

#################INTERFAZ POSTRES################################
framePostres = Frame(ventanaPrincipal)
#Cargamos las imagenes
renderArrozConLeche = ImageTk.PhotoImage(Image.open('images/arrozconleche.jpg'))
renderFlan = ImageTk.PhotoImage(Image.open('images/flan.jpg'))
renderFruta = ImageTk.PhotoImage(Image.open('images/fruta.jpg'))
renderGelatina = ImageTk.PhotoImage(Image.open('images/gelatina.jpg'))
renderNatillas = ImageTk.PhotoImage(Image.open('images/natillas.jpg'))
renderTartaChocolate = ImageTk.PhotoImage(Image.open('images/tarta3chocolates.jpg'))
renderTartaManzana = ImageTk.PhotoImage(Image.open('images/tartamanzana.jpg'))
renderTorrijas = ImageTk.PhotoImage(Image.open('images/torrijas.jpg'))
renderYogur = ImageTk.PhotoImage(Image.open('images/yogur.jpg'))

#Creamos el titulo
tituloPostres = Label(framePostres,text="Oferta de postres")
tituloPostres.config(padx=5,pady=5,anchor = CENTER,fg="#d16200",font=("Verdana",24))

#Anhadimos las imagenes al frame
imgArrozConLeche = Label(framePostres,text="Arroz con leche",font=("Verdana",12),image = renderArrozConLeche,compound=TOP) #El atributo "compound" permite indicar como se colocan el texto y la imagen (entre si)
imgFlan = Label(framePostres,text="Flan",font=("Verdana",12),image = renderFlan,compound=TOP)
imgFruta = Label(framePostres,text="Fruta",font=("Verdana",12),image = renderFruta,compound=TOP)
imgGelatina = Label(framePostres,text="Gelatina",font=("Verdana",12),image = renderGelatina,compound=TOP)
imgNatillas = Label(framePostres,text="Natillas",font=("Verdana",12),image = renderNatillas,compound=TOP)
imgTartaChocolate = Label(framePostres,text="Tarta 3 chocolates",font=("Verdana",12),image = renderTartaChocolate,compound=TOP)
imgTartaManzana = Label(framePostres,text="Tarta de manzana",font=("Verdana",12),image = renderTartaManzana,compound=TOP)
imgTorrijas = Label(framePostres,text="Torrijas",font=("Verdana",12),image = renderTorrijas,compound=TOP)
imgYogur = Label(framePostres,text="Yogur",font=("Verdana",12),image = renderYogur,compound=TOP)

#Colocamos los elementos en el frame
tituloPostres.grid(columnspan = 4) #Centramos el titulo
imgArrozConLeche.grid(padx=5, pady=5,row=1,column=0)
imgFlan.grid(padx=5, pady=5,row=1,column=1)
imgFruta.grid(padx=5, pady=5,row=1,column=2)
imgGelatina.grid(padx=5, pady=5,row=2,column=0)
imgNatillas.grid(padx=5, pady=5,row=2,column=1)
imgTartaChocolate.grid(padx=5, pady=5,row=2,column=2)
imgTartaManzana.grid(padx=5, pady=5,row=3,column=0)
imgTorrijas.grid(padx=5, pady=5,row=3,column=1)
imgYogur.grid(padx=5, pady=5,row=3,column=2)

#################INTERFAZ MENU DIARIO################################
frameMenuDiario = Frame(ventanaPrincipal)
#Cargamos las imagenes
renderPrimero = ImageTk.PhotoImage(Image.open('images/tortilla.jpg'))
renderSegundo = ImageTk.PhotoImage(Image.open('images/arroz.jpg'))
renderPostre = ImageTk.PhotoImage(Image.open('images/natillas.jpg'))

#Creamos el titulo
tituloMenuDiario = Label(frameMenuDiario,text="Menú de hoy")
tituloMenuDiario.config(padx=5, pady=5,anchor = CENTER, fg="#d16200",font=("Verdana",24))
subtituloMenuDiario = Label(frameMenuDiario,text="Jueves 18 de Noviembre")
subtituloMenuDiario.config(padx=5, pady=5,anchor = CENTER, fg="#d16200",font=("Verdana",18))
comentarioMenuDiario = Label(frameMenuDiario,text="Realiza el gesto de aceptar para comprar un menú")
comentarioMenuDiario.config(padx=5, pady=5,anchor = CENTER, fg="blue",font=("Verdana",18))

#Anhadimos las imagenes al frame
imgPrimero = Label(frameMenuDiario,text="Primer plato: Tortilla",font=("Verdana",12),image = renderPrimero,compound=TOP)
imgSegundo = Label(frameMenuDiario,text="Segundo plato: Arroz al horno",font=("Verdana",12),image = renderSegundo,compound=TOP)
imgPostre = Label(frameMenuDiario,text="Postre: Natillas",font=("Verdana",12),image = renderPostre,compound=TOP)

#Colocamos los elementos en el frame
tituloMenuDiario.grid(columnspan = 4) #Centramos el titulo
subtituloMenuDiario.grid(columnspan=4)
imgPrimero.grid(padx=5, pady=40,row=2,column=0)
imgSegundo.grid(padx=5, pady=40,row=2,column=1)
imgPostre.grid(padx=5, pady=5,columnspan=4)
comentarioMenuDiario.grid(padx=5, pady=5,columnspan=4)

#################INTERFAZ PAGO################################
framePago = Frame(ventanaPrincipal)

#Cargamos las imagenes
renderLogoUGR = ImageTk.PhotoImage(Image.open('images/logo-UGR.jpg'))
renderOkMano = ImageTk.PhotoImage(Image.open('images/ok_mano.jpg'))
renderPagoTarjeta = ImageTk.PhotoImage(Image.open('images/pagoTarjeta.jpg'))

#Creamos el titulo
tituloPago = Label(framePago,text="Ventana de pago")
tituloPago.config(padx=5, pady=5,anchor = CENTER, fg="#d16200",font=("Verdana",24))

#Anhadimos las imagenes al frame
imgOK = Label(framePago,image = renderOkMano)
imgLogoUGR = Label(framePago, image = renderLogoUGR)
imgpagoTarjeta = Label(framePago,image = renderPagoTarjeta)
textoPago = Label(framePago,text="Acerque tarjeta a TPV para pagar", fg="black",font=("Verdana",24))
textoAgradecimiento = Label(framePago,text="Gracias por confiar en nuestros servicios", fg="black",font=("Verdana",24))

#Colocamos los elementos en el frame
tituloPago.grid(columnspan = 2) #Centramos el titulo
imgOK.grid(padx=10, pady=(50, 25),row=1,column=0, columnspan = 2)
imgpagoTarjeta.grid(padx=10, pady=10, row=3, column=0)
textoPago.grid(padx=10, pady=10,row=2,column=0, columnspan = 2)
imgLogoUGR.grid(padx=10, pady=10, row=3, column=1)
textoAgradecimiento.grid(padx=10, pady=10,row=4,column=0, columnspan = 2)


######FLUJO ENTRE INTERFACES######
ventanaParking = 0
boolPrincipal = True
boolParking = False
boolComedor = False
boolActividades = False
boolPrimeros = False
boolSegundos = False
boolPostres = False
boolMenuDiario = False
boolPago = False


def goPrincipal():
    global boolPrincipal
    global framePrincipal

    boolPrincipal = True

    framePrincipal.pack()

def goParking():
    global boolParking
    global boolPrincipal
    global framePrincipal
    global frameParking

    boolParking = True

    if boolPrincipal:
        boolPrincipal = False
        framePrincipal.pack_forget()

    frameParking.pack()


def goActividades():
    global boolActividades
    global boolPrincipal
    global framePrincipal
    global frameActividades

    boolActividades = True

    if boolPrincipal:
        boolPrincipal = False
        framePrincipal.pack_forget()

    frameActividades.pack(expand=True, fill="both")

def mover(inclinacion):
    if inclinacion > 0:
        canvas.yview_scroll(-1, "units")
    else:
        canvas.yview_scroll(1, "units")


def goComedor():
    global boolComedor
    global boolPrincipal
    global framePrincipal
    global frameComedor

    boolComedor= True

    if boolPrincipal:
        boolPrincipal = False
        framePrincipal.pack_forget()

    frameComedor.pack()

def goPrimeros():
    global boolComedor
    global boolPrimeros
    global framePrimeros
    global frameComedor

    boolPrimeros = True

    if boolComedor:
        boolComedor = False
        frameComedor.pack_forget()

    framePrimeros.pack()

def goSegundos():
    global boolComedor
    global boolSegundos
    global frameComedor
    global frameSegundos

    boolSegundos = True

    if boolComedor:
        boolComedor= False
        frameComedor.pack_forget()

    frameSegundos.pack()

def goPostres():
    global boolComedor
    global boolPostres
    global frameComedor
    global framePostres

    boolPostres = True

    if boolComedor:
        boolComedor = False
        frameComedor.pack_forget()

    framePostres.pack()

def goMenuDiario():
    global boolComedor
    global boolMenuDiario
    global frameComedor
    global frameMenuDiario

    boolMenuDiario = True

    if boolComedor:
        boolComedor= False
        frameComedor.pack_forget()

    frameMenuDiario.pack()

def goPago():
    global boolPago
    global boolMenuDiario
    global framePago
    global frameMenuDiario

    boolPago= True

    if boolMenuDiario:
        boolMenuDiario = False
        frameMenuDiario.pack_forget()

    framePago.pack()

def volverAtras():
    global boolComedor
    global boolParking
    global boolActividades
    global boolPrimeros
    global boolSegundos
    global boolPostres
    global boolMenuDiario
    global boolPago
    global frameComedor
    global frameParking
    global frameActividades
    global framePrimeros
    global frameSegundos
    global framePostres
    global frameMenuDiario
    global framePago

    if boolComedor:
        boolComedor = False
        frameComedor.pack_forget()
        goPrincipal()
    elif boolParking:
        boolParking = False
        frameParking.pack_forget()
        goPrincipal()
    elif boolActividades:
        boolActividades = False
        frameActividades.pack_forget()
        goPrincipal()
    elif boolPrimeros:
        boolPrimeros = False
        framePrimeros.pack_forget()
        goComedor()
    elif boolSegundos:
        boolSegundos = False
        frameSegundos.pack_forget()
        goComedor()
    elif boolPostres:
        boolPostres = False
        framePostres.pack_forget()
        goComedor()
    elif boolMenuDiario:
        boolMenuDiario = False
        frameMenuDiario.pack_forget()
        goComedor()
    elif boolPago:
        boolPago = False
        framePago.pack_forget()
        goPrincipal()

#######################################################################################################################

class LeapListener(Leap.Listener):
    finger_names = ['Thumb', 'Index', 'Middle', 'Ring', 'Pinky']
    bone_names = ['Metacarpal', 'Proximal', 'Intermediate', 'Distal']
    state_names = ['STATE_INVALID', 'STATE_START', 'STATE_UPDATE', 'STATE_END']

    def on_init(self, controller):
        print("Initialized")

    def on_connect(self, controller):
        print("Connected")
        # Enable gestures
        controller.enable_gesture(Leap.Gesture.TYPE_CIRCLE);
        controller.enable_gesture(Leap.Gesture.TYPE_KEY_TAP);
        controller.enable_gesture(Leap.Gesture.TYPE_SCREEN_TAP);
        controller.enable_gesture(Leap.Gesture.TYPE_SWIPE);

    def on_disconnect(self, controller):
        # Note: not dispatched when running in a debugger.
        print("Disconnected")

    def on_exit(self, controller):
        print("Exited")

    def on_frame(self, controller):
        global boolPrincipal
        global boolComedor
        global boolParking
        global boolActividades
        global boolPrimeros
        global boolSegundos
        global boolPostres
        global boolMenuDiario
        global boolPago

        # Get the most recent frame and report some basic information
        frame = controller.frame()

        # Get hands
        for hand in frame.hands:
            direction = hand.direction

            #Obtenemos los distintos dedos de la mano
            thumb = hand.fingers[0]
            index = hand.fingers[1]
            middle = hand.fingers[2]
            ring = hand.fingers[3]
            little = hand.fingers[4]

            #Gstos del comedor
            if (boolPrincipal and (index.is_extended or middle.is_extended) and not(thumb.is_extended) and not(ring.is_extended) and not(little.is_extended)) and\
               (abs(hand.palm_velocity[0]) > 50 and hand.palm_velocity[1] > 90 and hand.palm_velocity[2] > 90):
                print("A comer")
                goComedor()
                time.sleep(1.0)

            if (boolComedor and index.is_extended and not(middle.is_extended) and not(thumb.is_extended) and not(ring.is_extended) and not(little.is_extended)) and\
                (abs(hand.palm_velocity[1]) < 50 and abs(hand.palm_velocity[0]) < 50 and abs(hand.palm_velocity[2]) < 50):
                print("Primer plato")
                goPrimeros()
                time.sleep(1.0)

            if (boolComedor and index.is_extended and middle.is_extended and not(thumb.is_extended) and not(ring.is_extended) and not(little.is_extended)) and\
                (abs(hand.palm_velocity[1]) < 50 and abs(hand.palm_velocity[0]) < 50 and abs(hand.palm_velocity[2]) < 50):
                print("Segundo plato")
                goSegundos()
                time.sleep(1.0)
                return

            if ((boolComedor and index.is_extended and middle.is_extended and thumb.is_extended and not(ring.is_extended) and not(little.is_extended)) or \
                (boolComedor and not(index.is_extended) and middle.is_extended and not(thumb.is_extended) and ring.is_extended and little.is_extended) or \
                (boolComedor and index.is_extended and middle.is_extended and not(thumb.is_extended) and ring.is_extended and not(little.is_extended))) and\
                (abs(hand.palm_velocity[1]) < 50 and abs(hand.palm_velocity[0]) < 50 and abs(hand.palm_velocity[2]) < 50):
                print("Postre")
                goPostres()
                time.sleep(1.0)
                return

            #Gestos del menu entero y del pago aceptado
            if boolComedor and index.is_extended and middle.is_extended and thumb.is_extended and ring.is_extended and little.is_extended:
                print("Menu entero")
                goMenuDiario()
                time.sleep(1.0)

            if boolMenuDiario and not(index.is_extended) and not(middle.is_extended) and thumb.is_extended and not(ring.is_extended) and not(little.is_extended):
                print("Pago aceptado")
                goPago()
                time.sleep(1.0)

            # Gestos del parking
            if (boolPrincipal and not(index.is_extended) and not(middle.is_extended) and not(thumb.is_extended) and not(ring.is_extended) and not(little.is_extended)) and \
               abs(hand.palm_velocity[0]) < 30 and abs(hand.palm_velocity[1]) > 175 and abs(hand.palm_velocity[2]) < 75 and abs(direction.pitch * Leap.RAD_TO_DEG) < 90:
                print("Arranca motor")
                goParking()
                time.sleep(1.0)

            # Gestos de Actividades de la semana
            if (boolPrincipal and index.is_extended and middle.is_extended and thumb.is_extended and ring.is_extended and little.is_extended) and\
                (abs(hand.palm_velocity[1]) < 50 and abs(hand.palm_velocity[0]) < 50 and abs(hand.palm_velocity[2]) < 50):
                print("Actividades")
                goActividades()
                time.sleep(1.0)

            if boolActividades and abs(direction.pitch * Leap.RAD_TO_DEG) > 20 and index.is_extended and middle.is_extended and ring.is_extended and little.is_extended:
                print("Moverse con Scroll")
                mover(direction.pitch)
                time.sleep(0.05)


        for gesture in frame.gestures():
            #Volver atras
            if gesture.type == Leap.Gesture.TYPE_CIRCLE and index.is_extended and not(middle.is_extended) and not(ring.is_extended) and not(little.is_extended):
                print("Volver Atras")
                volverAtras()
                time.sleep(0.75)
                return



def main():
    listener = LeapListener()
    controller = Leap.Controller()
    controller.add_listener(listener)

    ventanaPrincipal.mainloop()


if __name__ == "__main__":
    main()
