    package com.example.elearning.model;

    import com.fasterxml.jackson.annotation.JsonManagedReference;
    import jakarta.persistence.*;
    import java.util.HashSet;
    import java.util.Set;

    @Entity
    @Table(name = "modules")
    public class Module {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String topic;

        @Column(length = 1000)
        private String description;

        @ManyToOne
        @JoinColumn(name = "course_id", nullable = false)
        private Course course;

        @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true)
        @JsonManagedReference
        private Set<Quiz> quizzes = new HashSet<>();


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Course getCourse() {
            return course;
        }

        public void setCourse(Course course) {
            this.course = course;
        }

        public Set<Quiz> getQuizzes() {
            return quizzes;
        }

        public void setQuizzes(Set<Quiz> quizzes) {
            this.quizzes = quizzes;
        }
    }