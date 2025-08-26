package com.dspatched.test;

import java.util.*;
import java.util.concurrent.Flow;

public class TestTask {

    public Deque<Patient> queue;
    List<Terapeut> terapeuts;

    public static void main(String[] args) {
        TestTask testTask = new TestTask();
        testTask.terapeuts.add(new Terapeut(new LinkedList<Patient>()));
        testTask.terapeuts.add(new Terapeut(new LinkedList<Patient>()));
        testTask.terapeuts.add(new Terapeut(new LinkedList<Patient>()));
    }

    // красный  - вне очереди незамедл, варч прерывает
    // жельый - без очереди
    // зеленый - в порядке очереди
    // 3 врача

    public void registerPatient(Patient patient) {
        String curPriority = patient.getPriority();

        Scanner scanner = new Scanner(System.in);

        String input = scanner.next();
        int vert = 0;
        int hor = 0;
        for (int i = 0; i < input.length(); i++) {
            char dir = input.charAt(i);
            switch (dir) {
                case 'N' -> vert++;
                case 'S' -> vert--;
                case 'W' -> hor++;
                case 'E' -> hor--;
            }
        }

        terapeuts.forEach(number -> System.out.print(number + " "));
        switch (curPriority) {
            case "RED" -> {
               var doctor = this.getFreeDoctorForRed();
               if (doctor.isPresent()) {
                   doctor.get().queue.offer(patient);
               } else {
                   this.doctorWithShortestQueue().queue.offer(patient);
               }
            }
            case "YELLOW" -> {
                var doctor = this.getFreeDoctorForYellow();
                if (doctor.isPresent()) {
                    doctor.get().queue.offer(patient);
                } else {
                    this.doctorWithShortestQueue().queue.offer(patient);
                }
            }
            case "GREEN" -> {
                this.doctorWithShortestQueue().queue.offer(patient);
            }
        }
    }

    private Terapeut doctorWithShortestQueue() {
        int length = Integer.MAX_VALUE;
        Terapeut freeDoctor = terapeuts.get(0);
        for (Terapeut doctor : terapeuts) {
            if (doctor.queue.size() < length) {
                length = doctor.queue.size();
                freeDoctor = doctor;
            }
        }
        return freeDoctor;
    }

    private Optional<Terapeut> getFreeDoctorForRed() {
        return terapeuts.stream()
                .filter(terapeut -> terapeut.checkIfFree() || !terapeut.checkCurrent().equals("RED") )
                .findFirst();
    }

    private Optional<Terapeut> getFreeDoctorForYellow() {
        return terapeuts.stream()
                .filter(terapeut -> terapeut.checkIfFree() || terapeut.checkCurrent().equals("GREEN") )
                .findFirst();
    }

}

class Patient {

    long id;
    String priority;

    String getPriority() {
        return this.priority;
    }

}

class Terapeut {

    public Deque<Patient> queue;

    public Terapeut(Deque<Patient> queue) {
        this.queue = queue;
    }

    public Deque<Patient> getQueue() {
        return queue;
    }

    public boolean checkIfFree() {
        return queue.isEmpty();
    }

    public String checkCurrent() {
        return queue.peek().getPriority();
    }

}
