package com.nlh.projects.util;

import com.nlh.projects.models.*;
import com.nlh.projects.payloads.response.Projects;
import com.nlh.projects.repository.entity.*;
import com.nlh.projects.repository.entity.Project;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

public abstract class WrapperProject {

    public static List<Projects> convertTo (List<Project> projectList){
        List<Projects> projects = new ArrayList<>();
        projectList.forEach(project ->{
            if(Objects.nonNull(project.getCreatedAt())) {
                projects.add(new Projects(project.getId(), project.getName(), Date.from(project.getCreatedAt())));
            }
        });
        return projects;
    }

    public static Project projectFrom (com.nlh.projects.models.Project modelProject){
        Project entity = new Project();
        entity.setName(modelProject.getName());
        entity.setDescription(modelProject.getDescription());
        entity.setStartDate(dateFrom(modelProject.getStartDate()));
        entity.setEnable(modelProject.getEnable());
        entity.setProjectGenerals(generalFrom(modelProject.getGeneral(), entity));
        entity.setProjectDetails(detailFrom(modelProject.getDetail(), entity));
        entity.setProjectExtras(extraFrom(modelProject.getExtra(), entity));
        entity.setProjectOfficers(officerFrom(modelProject.getOfficer(), entity));
        return entity;
    }

    private static ProjectOfficer officerFrom(@NotNull Officer officer, Project project) {
        ProjectOfficer entity = new ProjectOfficer();
        entity.setContact(officer.getContact());
        entity.setProjectOfficerStaffs(projectOfficerStaffFrom(officer.getOfficers(), entity));
        entity.setProject(project);
        return entity;
    }

    private static Set<ProjectOfficerStaff> projectOfficerStaffFrom(@NotNull List<OfficerStaff> officers, ProjectOfficer projectOfficer) {
        return officers.stream()
                .map(officerStaff -> {
                    ProjectOfficerStaff projectOfficerStaff = new ProjectOfficerStaff();
                    projectOfficerStaff.setName(officerStaff.getName());
                    projectOfficerStaff.setTypeStaff(typeStaffFrom(officerStaff));
                    projectOfficerStaff.setProjectOfficer(projectOfficer);
                    return projectOfficerStaff;
                }).collect(Collectors.toSet());
    }

    private static @NotNull ProjectTypeStaff typeStaffFrom(OfficerStaff officerStaff) {
        ProjectTypeStaff entity = new ProjectTypeStaff();
        entity.setId(officerStaff.getTypeStaffId());
        return entity;
    }

    private static ProjectExtra extraFrom(@NotBlank Extra extra, Project project) {
        ProjectExtra entity = new ProjectExtra();
        entity.setAdditional(extra.getAdditional());
        entity.setProject(project);
        return entity;
    }

    private static ProjectDetail detailFrom(@NotNull Detail detail, Project project) {
        ProjectDetail entity = new ProjectDetail();
        entity.setFlats(Long.valueOf(detail.getFlats()));
        entity.setType(detail.getType());
        entity.setQuantity(Long.valueOf(detail.getQuantity()));
        entity.setSurface(detail.getSurface());
        entity.setRawMaterial(detail.getRawMaterial());
        entity.setProjectDetailAreas(projectDetailAreaFrom(detail.getAreas(), entity));
        entity.setProject(project);
        return entity;
    }

    private static Set<ProjectDetailArea> projectDetailAreaFrom(@NotNull List<Area> areas, ProjectDetail projectDetail) {
        return areas.stream()
                .map(area -> {
                    ProjectDetailArea projectDetailArea = new ProjectDetailArea();
                    projectDetailArea.setDescription(area.getDescription());
                    projectDetailArea.setProjectDetails(projectDetail);
                    return projectDetailArea;
                }).collect(Collectors.toSet());
    }

    private static ProjectGeneral generalFrom(@NotNull General general, Project project) {
        ProjectGeneral entity = new ProjectGeneral();
        entity.setDescription(general.getDescription());
        entity.setLocation(general.getLocation());
        entity.setProject(project);
        return entity;
    }

    public static Date dateFrom (LocalDate localDate){
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDate.atStartOfDay(), ZoneId.systemDefault());
        Instant instant = zonedDateTime.toInstant();
        return Date.from(instant);
    }

    public static LocalDate dateFrom (Date date){
        Instant instant = date.toInstant();
        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }


    public static Project projectUpdateFrom(Project projectCreated, Project projectUpgrade) {
        projectCreated.setName(projectUpgrade.getName());
        projectCreated.setDescription(projectUpgrade.getDescription());
        projectCreated.setStartDate(projectUpgrade.getStartDate());
        projectCreated.setEnable(projectUpgrade.getEnable());
        projectCreated.setProjectGenerals(generalUpdateFrom(projectCreated.getProjectGenerals(),projectUpgrade.getProjectGenerals()));
        // projectCreated.setProjectDetails(detailUpdateFrom(projectCreated.getProjectDetails(),projectUpgrade.getProjectDetails()));
        projectCreated.setProjectExtras(extraUpdateFrom(projectCreated.getProjectExtras(), projectUpgrade.getProjectExtras()));
        // projectCreated.setProjectOfficers(officerUpdateFrom(projectCreated.getProjectOfficers(), projectUpgrade.getProjectOfficers()));
        return projectCreated;
    }

    private static ProjectOfficer officerUpdateFrom(ProjectOfficer created, ProjectOfficer upgrade) {
        return created;
    }

    private static ProjectExtra extraUpdateFrom(ProjectExtra created, ProjectExtra upgrade) {
        created.setAdditional(upgrade.getAdditional());
        return created;
    }

    private static ProjectDetail detailUpdateFrom(ProjectDetail created, ProjectDetail update) {
        created.setType(update.getType());
        created.setQuantity(update.getQuantity());
        created.setSurface(update.getSurface());
        created.setRawMaterial(update.getRawMaterial());
        created.setFlats(update.getFlats());
        created.setProjectDetailAreas(projectDetailAreaUpdateFrom(created.getProjectDetailAreas(),update.getProjectDetailAreas()));
        return created;
    }


    private static Set<ProjectDetailArea> projectDetailAreaUpdateFrom(Set<ProjectDetailArea> areasCreated, Set<ProjectDetailArea> areasUpgrade) {
        List<ProjectDetailArea> created = new ArrayList<>(areasCreated);
        List<ProjectDetailArea> upgrade = new ArrayList<>(areasUpgrade);

        int sizeAreasCreated = created.size();
        int sizeAreasUpgrade = upgrade.size();

        if (sizeAreasCreated == sizeAreasUpgrade) {
            for (int i = 0; i < created.size(); i++) {
                created.get(i).setDescription(upgrade.get(i).getDescription());
            }
        } else if (sizeAreasCreated < sizeAreasUpgrade) {

        } else {

        }

        return Set.copyOf(created);
    }
    private static ProjectGeneral generalUpdateFrom(ProjectGeneral created, ProjectGeneral updated) {
        created.setDescription(updated.getDescription());
        created.setLocation(updated.getLocation());
        return created;
    }

}
