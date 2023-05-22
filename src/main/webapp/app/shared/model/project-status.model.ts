import { IProject } from '@/shared/model/project.model';
import { IProjectStatusCode } from '@/shared/model/project-status-code.model';

export interface IProjectStatus {
  id?: number;
  statusDate?: Date | null;
  statusTitle?: string | null;
  updatedProgress?: number | null;
  updatedWorkloadEAC?: number | null;
  updatedEndDate?: Date | null;
  updatedCost?: number | null;
  dateCreation?: Date | null;
  dateModify?: Date | null;
  lastModifyBy?: string | null;
  createdBy?: string | null;
  notes?: string | null;
  projectName?: IProject | null;
  projectStatusCode?: IProjectStatusCode | null;
}

export class ProjectStatus implements IProjectStatus {
  constructor(
    public id?: number,
    public statusDate?: Date | null,
    public statusTitle?: string | null,
    public updatedProgress?: number | null,
    public updatedWorkloadEAC?: number | null,
    public updatedEndDate?: Date | null,
    public updatedCost?: number | null,
    public dateCreation?: Date | null,
    public dateModify?: Date | null,
    public lastModifyBy?: string | null,
    public createdBy?: string | null,
    public notes?: string | null,
    public projectName?: IProject | null,
    public projectStatusCode?: IProjectStatusCode | null
  ) {}
}
