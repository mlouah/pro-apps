import { IMoM } from '@/shared/model/mo-m.model';
import { ITask } from '@/shared/model/task.model';
import { IProjectPriority } from '@/shared/model/project-priority.model';
import { IProjectCategory } from '@/shared/model/project-category.model';
import { IClient } from '@/shared/model/client.model';
import { IPerson } from '@/shared/model/person.model';
import { ICompany } from '@/shared/model/company.model';
import { IProjectStatusCode } from '@/shared/model/project-status-code.model';

export interface IProject {
  id?: number;
  name?: string;
  code?: string;
  startDate?: Date | null;
  realEndtDate?: Date | null;
  projectDescription?: string | null;
  initialEndDate?: Date | null;
  initialCost?: number | null;
  initialWorkLoad?: number | null;
  dateCreation?: Date | null;
  dateModify?: Date | null;
  lastModifyBy?: string | null;
  createdBy?: string | null;
  notes?: string | null;
  alertes?: string | null;
  todo?: string | null;
  progress?: number | null;
  moMTitles?: IMoM[] | null;
  tasks?: ITask[] | null;
  projectPriority?: IProjectPriority | null;
  projectCategory?: IProjectCategory | null;
  clientCode?: IClient | null;
  internalProjectManager?: IPerson | null;
  company?: ICompany | null;
  projectStatusCode?: IProjectStatusCode;
}

export class Project implements IProject {
  constructor(
    public id?: number,
    public name?: string,
    public code?: string,
    public startDate?: Date | null,
    public realEndtDate?: Date | null,
    public projectDescription?: string | null,
    public initialEndDate?: Date | null,
    public initialCost?: number | null,
    public initialWorkLoad?: number | null,
    public dateCreation?: Date | null,
    public dateModify?: Date | null,
    public lastModifyBy?: string | null,
    public createdBy?: string | null,
    public notes?: string | null,
    public alertes?: string | null,
    public todo?: string | null,
    public progress?: number | null,
    public moMTitles?: IMoM[] | null,
    public tasks?: ITask[] | null,
    public projectPriority?: IProjectPriority | null,
    public projectCategory?: IProjectCategory | null,
    public clientCode?: IClient | null,
    public internalProjectManager?: IPerson | null,
    public company?: ICompany | null,
    public projectStatusCode?: IProjectStatusCode
  ) {}
}
