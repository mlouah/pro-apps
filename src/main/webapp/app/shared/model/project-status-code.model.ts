import { IProjectStatus } from '@/shared/model/project-status.model';

export interface IProjectStatusCode {
  id?: number;
  code?: string;
  status?: string | null;
  dateCreation?: Date | null;
  dateModify?: Date | null;
  lastModifyBy?: string | null;
  createdBy?: string | null;
  notes?: string | null;
  projectStatuses?: IProjectStatus[] | null;
}

export class ProjectStatusCode implements IProjectStatusCode {
  constructor(
    public id?: number,
    public code?: string,
    public status?: string | null,
    public dateCreation?: Date | null,
    public dateModify?: Date | null,
    public lastModifyBy?: string | null,
    public createdBy?: string | null,
    public notes?: string | null,
    public projectStatuses?: IProjectStatus[] | null
  ) {}
}
