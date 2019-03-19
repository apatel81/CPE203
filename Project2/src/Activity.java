public class Activity implements Action
{

    private Entity entity;
    private WorldModel world;
    private ImageStore imageStore;
    private int repeatCount;

    public Activity(Entity entity, WorldModel world, ImageStore imageStore, int repeatCount)
    {
       this.entity = entity;
       this.world = world;
       this.imageStore = imageStore;
       this.repeatCount = repeatCount;
    }

    public Entity getEntity()
    {
        return this.entity;
    }

    public void setEntity(Entity e)
    {
        this.entity = e;
    }

    public WorldModel getWorld()
    {
        return this.world;
    }

    public void setWorld(WorldModel w)
    {
        this.world = w;
    }

    public ImageStore getImageStore()
    {
        return this.imageStore;
    }

    public void setImageStore(ImageStore i)
    {
        this.imageStore = i;
    }

    public int getRepeatCount()
    {
        return this.repeatCount;
    }

    public void setRepeatCount(int r)
    {
        this.repeatCount = r;
    }


    public void executeAction(EventScheduler scheduler)
    {
        executeActivityAction(scheduler);
    }


    public void executeActivityAction(EventScheduler scheduler)
    {
        if (this.entity instanceof MinerFull)
        {
//            ((MinerFull)this.entity).executeMinerFullActivity(this.world, this.imageStore, scheduler);
            ((MinerFull) this.entity).executeActivity(this.world, this.imageStore, scheduler);
        }

        else if (this.entity instanceof MinerNotFull)
        {
//            ((MinerNotFull) this.entity).executeMinerNotFullActivity(this.world, this.imageStore, scheduler);
            ((MinerNotFull) this.entity).executeActivity(this.world, this.imageStore, scheduler);
        }

        else if (this.entity instanceof Ore)
        {
//            ((Ore) this.entity).executeOreActivity(this.world, this.imageStore, scheduler);
            ((Ore) this.entity).executeActivity(this.world, this.imageStore, scheduler);
        }

        else if (this.entity instanceof OreBlob)
        {
//            ((OreBlob) this.entity).executeOreBlobActivity(this.world, this.imageStore, scheduler);
            ((OreBlob) this.entity).executeActivity(this.world, this.imageStore, scheduler);
        }

        else if (this.entity instanceof Quake)
        {
//            ((Quake) this.entity).executeQuakeActivity(this.world, this.imageStore, scheduler);
            ((Quake) this.entity).executeActivity(this.world, this.imageStore, scheduler);
        }

        else if (this.entity instanceof Vein)
        {
//            ((Vein) this.entity).executeVeinActivity(this.world, this.imageStore, scheduler);
            ((Vein) this.entity).executeActivity(this.world, this.imageStore, scheduler);
        }

        else
            {
                throw new UnsupportedOperationException(
                String.format("executeActivityAction not supported for %s", "this.entity.kind"));
            }


    }

    public Action createActivityAction(WorldModel world, ImageStore imageStore)
    {
       return new Activity((Entity)this.entity, world, imageStore, 0);
    }


}