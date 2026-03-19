package com.smartcity.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "issue_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "issue_id")
    private Issue issue;
}